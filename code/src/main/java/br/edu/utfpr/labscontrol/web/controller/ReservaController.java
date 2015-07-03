package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.*;
import br.edu.utfpr.labscontrol.model.enumeration.RolesEnum;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.*;
import br.edu.utfpr.labscontrol.web.exceptions.IllegalHorarioException;
import br.edu.utfpr.labscontrol.web.exceptions.ReservaException;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import br.edu.utfpr.labscontrol.web.util.HorariosManha;
import br.edu.utfpr.labscontrol.web.util.HorariosNoite;
import br.edu.utfpr.labscontrol.web.util.HorariosTarde;
import br.edu.utfpr.labscontrol.web.util.JsfUtil;
import javafx.scene.paint.Material;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.concurrent.ExecutionException;


/**
 * Created by EdsonGustavo on 29/03/2015.
 */
@Controller
@Scope("view")
public class ReservaController extends CrudController<Reserva, Integer> {
    @Autowired private ReservaService reservaService;
    @Autowired private ReservaItemService reservaItemService;
    @Autowired private AmbienteService ambienteService;
    @Autowired private CategoriaEquipamentoService categoriaEquipamentoService;
    @Autowired private MaterialDeConsumoService materialDeConsumoService;
    private ScheduleModel scheduleModel;
    private ScheduleEvent scheduleEvent = new DefaultScheduleEvent();
    private final Calendar calendar = Calendar.getInstance();
    private String tipo;
    private String horaDeInicio;
    private String horaDeFim;
    private MaterialDeConsumo materialDeConsumo;
    private CategoriaEquipamento categoriaEquipamento;
    private BigDecimal quantidade;
    private BigDecimal qtdEstoque;
    private HorariosManha horariosManha;
    private HorariosTarde horariosTarde;
    private HorariosNoite horariosNoite;

    @Override
    protected void inicializar() {
        this.quantidade = BigDecimal.ZERO;
        populaSchedule();
    }

    private void populaSchedule() {
        this.scheduleModel = new LazyScheduleModel() {
            @Override
            public void loadEvents(Date start, Date end) {
                for (Reserva reserva : reservaService.findByDataBetween(start, end)) {
                    Calendar inicio = Calendar.getInstance();
                    inicio.setTime(reserva.getData());
                    inicio.set(inicio.get(Calendar.YEAR), inicio.get(Calendar.MONTH), inicio.get(Calendar.DATE), getHour(reserva.getHoraInicio()), getMinute(reserva.getHoraInicio()));

                    Calendar fim = Calendar.getInstance();
                    fim.setTime(reserva.getData());
                    fim.set(fim.get(Calendar.YEAR), fim.get(Calendar.MONTH), fim.get(Calendar.DATE), getHour(reserva.getHoraFim()), getMinute(reserva.getHoraFim()));

                    addEvent(new DefaultScheduleEvent(getTitle(reserva), inicio.getTime(), fim.getTime(), reserva));
                }
            }
        };
    }

    private String getTitle(Reserva reserva) {
        return " até " + getHorario(reserva.getHoraFim()) + " - " + reserva.getAmbiente().getIdentificacao();
    }

    @Override
    protected ICrudService<Reserva, Integer> getService() {
        return reservaService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/reserva/reservaForm.xhtml?faces-redirect=true";
    }

    public List<CategoriaEquipamento> completeCategoriaEquipamento(String nome) {
        return categoriaEquipamentoService.findByNomeContaining(nome);
    }

    public List<MaterialDeConsumo> completeMaterialDeConsumo(String nome) {
        return materialDeConsumoService.findByNomeContaining(nome);
    }

    public List<Ambiente> completeAmbiente(String identificacao) {
        return ambienteService.findByIdentificacaoContaining(identificacao);
    }

    public void iniciaHorarios() {
        this.horaDeInicio = "";
        this.horaDeFim = "";
        this.horariosManha = new HorariosManha();
        this.horariosTarde = new HorariosTarde();
        this.horariosNoite = new HorariosNoite();
    }

    public void incluirHorario() {
        List<Date> hrsManha = this.horariosManha.getSelectedHorarios();
        List<Date> hrsTarde = this.horariosTarde.getSelectedHorarios();
        List<Date> hrsNoite = this.horariosNoite.getSelectedHorarios();

        List<Date> allListas = new ArrayList<>();
        allListas.addAll(hrsManha);
        allListas.addAll(hrsTarde);
        allListas.addAll(hrsNoite);

        Date[] horas = getMenorEmaiorHora(allListas);

        this.horaDeInicio = getHorario(horas[0]);
        this.horaDeFim = getHorario(horas[1]);

        this.entity.setHoraInicio(horas[0]);
        this.entity.setHoraFim(horas[1]);
    }

    private Date[] getMenorEmaiorHora(List<Date> lista) {
        Date horas[] = new Date[2];
        try {
            Date menor = new SimpleDateFormat("HH:mm").parse("24:00");
            Date maior = new SimpleDateFormat("HH:mm").parse("00:00");

            for(Date hora : lista) {
                if (hora.before(menor)) {
                    menor = hora;
                }
                if (hora.after(maior)) {
                    maior = hora;
                }
            }
            horas[0] = menor;
            horas[1] = maior;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return horas;
    }

    public void onItemSelect(SelectEvent event) {
        Object o = event.getObject();
        if (o instanceof MaterialDeConsumo) {
            MaterialDeConsumo m = materialDeConsumoService.findById(((MaterialDeConsumo)o).getId());
            this.qtdEstoque = m.getQtdAtual();
        }
    }

    public void onDateSelect(SelectEvent selectEvent) {
        this.qtdEstoque = BigDecimal.ZERO;
        this.horaDeInicio = "__:__";
        this.horaDeFim = "__:__";
        reset();
        this.entity.setData((Date) selectEvent.getObject());
        this.entity.setUsuario(JsfUtil.getUsuarioLogado());
        this.entity.setReservasItens(new ArrayList<>());
        if (((Permissao)JsfUtil.getAttributeSession(JsfUtil.PERMISSAO_USUARIO_LOGADO)).getId() == RolesEnum.USER.ordinal() + 1) {
            this.entity.setOutroUsuario(JsfUtil.getUsuarioLogado().getUsername());
        }
        scheduleEvent = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject(), this.entity);
    }

    public void onEventSelect(SelectEvent selectEvent) {
        this.qtdEstoque = BigDecimal.ZERO;
        scheduleEvent = (ScheduleEvent) selectEvent.getObject();
        try {
            this.entity = (Reserva) scheduleEvent.getData();
            this.horaDeInicio = getHorario(this.entity.getHoraInicio());
            this.horaDeFim = getHorario(this.entity.getHoraFim());
            setId(this.entity.getId()); // utilizado para exclusão da reserva
        } catch (Exception e) {
            reset();
        }
    }

    public void addEvent() {
        try {
            validaHorario();
            validaDisponibilidadeDaSalaNoHorario();
            validaItens();
            this.entity.setConfirmada(Boolean.FALSE);
            if (scheduleEvent.getId() == null) {
                scheduleModel.addEvent(scheduleEvent);
            } else {
                scheduleModel.updateEvent(scheduleEvent);
            }
            save();
            scheduleEvent = new DefaultScheduleEvent();
            populaSchedule();
        } catch (Exception e) {
            addMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void delete() {
        if (this.entity.getConfirmada()) {
            estornaEstoque();
        }
        super.delete();
        populaSchedule();
    }

    private void validaDisponibilidadeDaSalaNoHorario() throws ReservaException {
        List<Reserva> reservas = this.reservaService.findByDataAndHoraInicioBetweenAndHoraFimBetweenAndAmbienteIdAndConfirmada(this.entity.getData(),
                                                                                                                               this.entity.getHoraInicio(),
                                                                                                                               this.entity.getHoraFim(),
                                                                                                                               this.entity.getAmbiente().getId(),
                                                                                                                                Boolean.TRUE);
        if (!reservas.isEmpty()) {
            throw new ReservaException("Já existe Reserva confirmada nesta data, horário e ambiente!");
        } else {
            reservas = this.reservaService.findByDataAndHoraInicioBetweenAndHoraFimBetweenAndAmbienteIdAndConfirmada(this.entity.getData(),
                                                                                                                     this.entity.getHoraInicio(),
                                                                                                                     this.entity.getHoraFim(),
                                                                                                                     this.entity.getAmbiente().getId(),
                                                                                                                     Boolean.FALSE);
            if (!reservas.isEmpty()) {
                addMessage("Já existe Reserva não confirmada nesta data, horário e ambiente!", FacesMessage.SEVERITY_WARN);
            }
        }
    }

    private void validaItens() throws IllegalArgumentException {
        if (this.entity != null && this.entity.getReservasItens() != null) {
            Boolean invalid = Boolean.FALSE;
            for (ReservaItem ri : this.entity.getReservasItens()) {
                if (ri.getMaterialDeConsumo() != null) {
                    invalid = (ri.getQuantidade().compareTo(ri.getMaterialDeConsumo().getQtdAtual()) == 1);
                    break;
                }
            }
            if (invalid) {
                throw new IllegalArgumentException("Existem itens com quantidade informada superior à quantidade em estoque. Favor verificar!");
            }
        }
    }

    private void validaHorario() throws IllegalHorarioException {
        if (this.entity.getHoraInicio().getTime() > this.entity.getHoraFim().getTime()) {
            throw new IllegalHorarioException("Hora de Início deve ser menor ou igual a Hora de Fim!");
        }
    }

    /**
     * Somente Adm, Atendente e o usuário que criou a Reserva pode excluir
     * @return TRUE se o usuário logado possuir ROLE de Administrador, Atendente ou for o Usuário que criou a Reserva
     */
    public Boolean podeExcluir() {
        if (this.entity.getId() != null && JsfUtil.getUsuarioLogado().getId() != this.entity.getUsuario().getId()) {
            if ( ((Permissao)JsfUtil.getAttributeSession(JsfUtil.PERMISSAO_USUARIO_LOGADO)).getId() == RolesEnum.USER.ordinal() + 1 ) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    /**
     * Somente Adm, Atendente e o usuário que criou a Reserva pode salvar
     * @return TRUE se o usuário logado possuir ROLE de Administrador, Atendente ou for o Usuário que criou a Reserva
     */
    public Boolean podeSalvar() {
        if (this.entity.getId() != null && JsfUtil.getUsuarioLogado().getId() != this.entity.getUsuario().getId()) {
            if ( ((Permissao)JsfUtil.getAttributeSession(JsfUtil.PERMISSAO_USUARIO_LOGADO)).getId() == RolesEnum.USER.ordinal() + 1 ) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    /**
     * Somente Adm e o Atendente pode confirmar a Reserva
     * @return TRUE se o usuário logado possuir ROLE de Administrador ou Atendente
     */
    public Boolean podeConfirmar() {
        //Somente ADM e ATENDENTE
        return ((Permissao)JsfUtil.getAttributeSession(JsfUtil.PERMISSAO_USUARIO_LOGADO)).getId() != RolesEnum.USER.ordinal() + 1;
    }

    public void addItem() {
        try {
            validaCabecalho();
            validaQuantidadeEmEstoque();

            ReservaItem reservaItem = new ReservaItem();
            reservaItem.setReserva(this.entity);
            reservaItem.setQuantidade(this.quantidade);
            if (this.tipo.equals("E")) {
                reservaItem.setCategoriaEquipamento(this.categoriaEquipamento);
            } else {
                reservaItem.setMaterialDeConsumo(this.materialDeConsumo);
            }

            this.entity.getReservasItens().add(reservaItem);
            this.reservaItemService.save(reservaItem);

            this.qtdEstoque = BigDecimal.ZERO;
            this.quantidade = null;
            this.categoriaEquipamento = null;
            this.materialDeConsumo = null;
        } catch (Exception e) {
            addMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
            e.printStackTrace();
        }
    }

    private void validaCabecalho() throws Exception {
        if (this.entity.getId() == null) {
            if (this.entity.getOutroUsuario() == null || this.entity.getHoraInicio() == null || this.entity.getHoraFim() == null || this.entity.getAmbiente() == null) {
                throw new IllegalArgumentException("Informe os dados do cabeçalho antes de inserir um item!");
            } else {
                this.entity.setConfirmada(Boolean.FALSE);
                getService().save(this.entity);
            }
        }
    }

    private void validaQuantidadeEmEstoque() throws IllegalArgumentException {
        if (this.tipo.equals("M")) {
            if (this.materialDeConsumo.getQtdAtual().subtract(this.quantidade, MathContext.DECIMAL64).compareTo(BigDecimal.ZERO) == -1) {
                throw new IllegalArgumentException("Não há quantidade em estoque o suficiente!");
            }
        }
    }

    public void excluirItem(ReservaItem reservaItem) {
        this.entity.getReservasItens().remove(reservaItem);
    }

    public void onEdit(RowEditEvent event) {
        ReservaItem reservaItem = (ReservaItem) event.getObject();
        try {
            reservaItemService.save(reservaItem);
        } catch (Exception e) {
            addMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
            e.printStackTrace();
        }
    }

    public String isConfirmada() {
        if (this.entity != null && this.entity.getConfirmada() != null && this.entity.getConfirmada()) {
            return "Confirmada";
        } else {
            return "Não Confirmada";
        }
    }

    public void confirmaReserva() {
        /* TODO Enviar email? se o usuário que está fazendo a reserva for um Atendente ou Adm, o campo Outro Usuário está
         * apenas para informar um string, e não um Usuário, sendo assim nào tenho a informação necessária para mandar o email par ao usuário
         */
        if (this.entity.getConfirmada()) {
            baixarEstoque();
        } else {
            estornaEstoque();
        }
        save();
    }

    private void estornaEstoque() {
        addOrSubstractFromReserva(Boolean.FALSE);
    }

    private void baixarEstoque() {
        addOrSubstractFromReserva(Boolean.TRUE);
    }

    private void addOrSubstractFromReserva(Boolean substract) {
        if (this.entity.getReservasItens() != null) {
            for(ReservaItem ri : this.entity.getReservasItens()) {
                if (ri.getMaterialDeConsumo() != null) {
                    MaterialDeConsumo m = ri.getMaterialDeConsumo();
                    if (substract) {
                        m.setQtdAtual(m.getQtdAtual().subtract(ri.getQuantidade()));
                    } else {
                        m.setQtdAtual(m.getQtdAtual().add(ri.getQuantidade()));
                    }
                    try {
                        materialDeConsumoService.save(m);
                    } catch (Exception e) {
                        addMessage("Erro ao  baixar estoque!", FacesMessage.SEVERITY_FATAL);
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
//        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        //TODO ao alterar o horario, recalcular e atualizar a reserva
//        event.getScheduleEvent().getData();
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
//        addMessage(message);
    }

    public ScheduleEvent getScheduleEvent() {
        return scheduleEvent;
    }

    public void setScheduleEvent(ScheduleEvent scheduleEvent) {
        this.scheduleEvent = scheduleEvent;
    }

    public ScheduleModel getScheduleModel() {
        return scheduleModel;
    }

    public void setScheduleModel(ScheduleModel scheduleModel) {
        this.scheduleModel = scheduleModel;
    }

    private Integer getHour(Date date) {
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    private Integer getMinute(Date date){
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    private String getHorario(Date date) {
        return new SimpleDateFormat("HH:mm").format(date);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public MaterialDeConsumo getMaterialDeConsumo() {
        return materialDeConsumo;
    }

    public void setMaterialDeConsumo(MaterialDeConsumo materialDeConsumo) {
        this.materialDeConsumo = materialDeConsumo;
    }

    public CategoriaEquipamento getCategoriaEquipamento() {
        return categoriaEquipamento;
    }

    public void setCategoriaEquipamento(CategoriaEquipamento categoriaEquipamento) {
        this.categoriaEquipamento = categoriaEquipamento;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(BigDecimal qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public String getHoraDeInicio() {
        return horaDeInicio;
    }

    public void setHoraDeInicio(String horaDeInicio) {
        this.horaDeInicio = horaDeInicio;
    }

    public String getHoraDeFim() {
        return horaDeFim;
    }

    public void setHoraDeFim(String horaDeFim) {
        this.horaDeFim = horaDeFim;
    }

    public HorariosManha getHorariosManha() {
        return horariosManha;
    }

    public void setHorariosManha(HorariosManha horariosManha) {
        this.horariosManha = horariosManha;
    }

    public HorariosNoite getHorariosNoite() {
        return horariosNoite;
    }

    public void setHorariosNoite(HorariosNoite horariosNoite) {
        this.horariosNoite = horariosNoite;
    }

    public HorariosTarde getHorariosTarde() {
        return horariosTarde;
    }

    public void setHorariosTarde(HorariosTarde horariosTarde) {
        this.horariosTarde = horariosTarde;
    }
}
