package br.edu.utfpr.labscontrol.web.framework;

import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.web.util.JsfUtil;
import org.apache.commons.lang.SerializationUtils;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.validation.ConstraintViolationException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


/**
 * Esta classe prove propriedades e metodos basicos para
 * utilizacao de um controller com acesso ao banco de dados
 * @param <T> entidade
 * @param <ID> identificacao da entidade
 */
public abstract class CrudController<T extends Object, ID extends Serializable> extends BaseController {

    /**
     * Tipo da entidade passada por generics
     */
    private Class<T> type;

    /**
     * Field corresponde a entidade passada por generics utilizada para persistir/alterar/deletar no banco de dados
     */
    protected T entity;

    /**
     * Field correspondente a entidade copiada by Edson G Tofolo
     */
    protected T entityCopied;

    /**
     * Field correspondente a entidade apenas utilizada para exibir informacoes
     */
    protected T entityView;

    /**
     * Lista de entidades passadas por generics
     */
    protected List<T> lsEntity = new ArrayList<T>();

    /**
     * Identificador passado por generics
     */
    protected ID id;

    /**
     * Retornar a implementação da interface ICrudService para que a classe possa realizar as operacoes de CRUD
     * @return Servico a ser utilizado para persistir, alterar, excluir, editar e pesquisar a entidade passada por generics
     */
    protected abstract ICrudService<T, ID> getService();

    /**
     * Obtendo o tipo da entidade passada por generics e instanciando uma nova entidade
     */
    public CrudController() {
        setType((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        create();
    }

    @PostConstruct
    public void init() {
        inicializar();
    }

    /**
     * Método a ser executado ao inicializar o metodo init
     */
    protected void inicializar() {
        if (this.entity.getClass().equals(this.type)) {
            setId((ID) getIdFromObj());
        } else {
            entity = newRecord();
        }
    }

    /**
     * Instancia uma nova entidade conforme seu tipo obtido por generics
     * @return
     */
    private T newRecord() {
        try {
            return (T) getType().newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Este metodo recupera um objeto do scopo de flash
     * e caso o mesmo nao seja nulo o atribui o valor da entidade
     */
    protected void postCreate() {
        Object entity = JsfUtil.getFlashParameter("editar");
        if (entity != null) {
            this.entity = (T) entity;
        }
    }

    /**
     * Cria uma nova instacia da entidade passada por generics
     */
    private void create() {
        entity = newRecord();
        postCreate();
    }

    /**
     * Cria uma nova instacia da entidade passada por generics
     */
    public void reset() {
        create();
    }

    /**
     * Metodo a ser chamado antes de persistir a entidade no banco de dados
     * @param entity entidade a ser persistida
     * @return entidade a ser persistida
     */
    protected T preProcessorSave(T entity)  {
        return entity;
    }

    /**
     * Metodo a ser chamado depois de persistir a unidade no banco de dados
     * @param entity entidade persistida
     * @return entidade persistida
     */
    protected T postProcessorSave(T entity)  {
        return entity;
    }

    /**
     * Método a ser invocado antes de persistir a entidade ao banco de dados
     * apenas se o retorno for true o metodo sera persistido.
     * @param entity entidade a ser persistida no banco de dados
     * @return TRUE a entidade sera persistida
     */
    protected Boolean validacaoSave(T entity) {
        return Boolean.TRUE;
    }

    /**
     * Metodo responsavel por adicionar ao scopo de flash a entidade
     * passada por parametro para que seja recuperada posteriormente para edicao
     * @param entity entidade a ser editada
     * @return retorna a pagina de cadastro da entidade
     */
    public String editar(T entity) {
        JsfUtil.setFlashParameter("editar", entity);
        return getUrlFormPage();
    }

    /**
     * Faz um clone do objeto 'entity' para o objeto 'entityCopied'
     */
    public void copy() {
        this.entityCopied = (T) SerializationUtils.clone((Serializable) this.entity);
    }

    /**
     * Faz um clone do objeto 'entityCopied'para o objeto 'entity'
     */
    public void paste() {
        try {
            this.entity = (T) SerializationUtils.clone((Serializable) this.entityCopied);

            String name = "";
            for (int i = 0; i < type.getMethods().length - 1; i++) {
                name = type.getMethods()[i].getName();
                if (name.equals("setId")) {
                    Method method = type.getMethods()[i];
                    method.invoke(this.entity, 0);
                    break;
                }
            }
            //TODO verificar se o Payara aceita java 8, lambdas e et
            //Arrays.asList(type.getMethods()).stream().filter(m -> m.getName().equals("setId")).findFirst().get().invoke(this.entity, 0);
            addMessage("Registro colado!", FacesMessage.SEVERITY_INFO);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retorna o valor do atributo "id" do objeto em questão
     * @return
     */
    private Integer getIdFromObj() {
        try {
            String name = "";
            Integer id = null;
            for (int i = 0; i < type.getMethods().length - 1; i++) {
                name = type.getMethods()[i].getName();
                if (name.equals("getId")) {
                    Method method = type.getMethods()[i];
                    id = (Integer) method.invoke(this.entity);
                    break;
                }
            }
            return id;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Passa para o próximo registro
     */
    public void next() {
        Integer id = (Integer) getId();
        T object = getService().findById((ID) (++id));
        if (object == null) {
            addMessage("Último registro!", FacesMessage.SEVERITY_WARN);
        } else {
            this.entity = object;
            setId((ID) getIdFromObj());
        }
    }

    /**
     * Volta um registro
     */
    public void previous() {
        Integer id = (Integer) getId();
        T object = getService().findById((ID) (--id));
        if (object == null) {
            addMessage("Primeiro registro!", FacesMessage.SEVERITY_WARN);
        } else {
            this.entity = object;
            setId((ID) getIdFromObj());
        }
    }

    /**
     * Vai para o último registro
     */
    public void last() {
        this.entity = getService().findLast();
        setId((ID) getIdFromObj());
    }

    /**
     * Vai para o primeiro registro
     */
    public void first() {
        this.entity = getService().findFirst();
        setId((ID) getIdFromObj());
    }

    /**
     * Este metodo deve retornar a pagina de cadastro da entidade
     * @return pagina de cadastro da entidade
     */
    protected abstract String getUrlFormPage();

    public abstract String getUrlSearchPage();

    /**
     * Método responsavel executar validacoes e invocar metodos de pre e post
     * persistencia, a ordem de invocacao dos metodos sao:
     *     validacaoSave;
     *     preProcessorSave;
     *     servico responsavel por persistir no banco de dados;
     *     postProcessorSave;
     * Em caso de sucesso ao persistir a entidade uma mensagem de sucesso é adicionada ao contexto
     * caso contrario uma mensagem contendo o erro lancado é adicionado ao contexto
     */
    public void save() {
        try {
            if (validacaoSave(entity)) {
                preProcessorSave(entity);
                getService().save(entity);
                postProcessorSave(entity);
                addMessage("Registro salvo com sucesso!", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception e) {
            addMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    protected void preProcessorDelete() throws Exception {

    }

    /**
     * Método responsavel por invocar o servico responsavel por deletar a entidade do banco de dados.
     * Em caso de sucesso o método de pesquisa é invocado e uma mensagem de sucesso é adicionada ao contexto.
     * Caso ocorrer um erro uma mensagem contento o erro lancado é adicionado ao contexto.
     */
    public void delete() {
        try {
            preProcessorDelete();
            getService().delete(getId());
            find();
            addMessage("Registro removido com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            addMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    protected void preProcessorDeleteFromForm() throws Exception {

    }

    /**
     * Faz o delete do objeto por dentro do formulário e logo após cria o objeto novamente para limpar os campos
     * Caso não ocorram erros na deleção do mesmo é feito um reset, caso contrário mantém os dados em tela
     */
    public void deleteFromForm() {
        try {
            preProcessorDeleteFromForm();
            getService().delete(getId());
            reset();
            addMessage("Registro removido com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            addMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    /**
     * Método responsavel por selecionar no banco de dados as informacoes
     * referentes a entidade
     */
    public void find() {
        lsEntity.clear();
        lsEntity.addAll(getService().findAll());
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    private Class<T> getType() {
        return type;
    }

    private void setType(Class<T> type) {
        this.type = type;
    }

    public List<T> getLsEntity() {
        return lsEntity;
    }

    public void setLsEntity(List<T> lsEntity) {
        this.lsEntity = lsEntity;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public T getEntityView() {
        return entityView;
    }

    public void setEntityView(T entityView) {
        this.entityView = entityView;
    }

}
