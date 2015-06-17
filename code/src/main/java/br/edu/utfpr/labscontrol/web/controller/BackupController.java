package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.web.framework.BaseController;
import br.edu.utfpr.labscontrol.web.util.JsfUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by EdsonGustavo on 16/06/2015.
 */
@Controller
@Scope("view")
public class BackupController extends BaseController {
    private static String PATH_BKP = "/var/www/html/edson/bkps_labscontrol/";
    private List<File> arquivos = new ArrayList<>();
    private String dbName;
    private String dbUser;
    private String dbPass;
    private File fileToExclude;

    @PostConstruct
    public void init() {
        this.dbName = JsfUtil.getDadosDBforBkp().get("dbName");
        this.dbUser = JsfUtil.getDadosDBforBkp().get("dbUser");
        this.dbPass = JsfUtil.getDadosDBforBkp().get("dbPass");
    }

    public void find() {
        File rootPath = new File(PATH_BKP);
        if (!rootPath.exists()) {
            rootPath.mkdirs();
        }
        File[] files = rootPath.listFiles();
        this.arquivos.clear();
        for (int j = files.length - 1; j >= 0; j--) {
            File file = files[j];
            this.arquivos.add(file);
            System.out.println(file.getName());
        }
    }

    /**
     * Para que seja possível a restauração do banco de dados há a necessidade de o usuário ter permissões para isso.
     * GRANT SELECT , RELOAD , FILE , SUPER , LOCK TABLES , SHOW VIEW ON * . * TO 'edson'@'localhost';
     * @param fileToRestore - Arquivo .sql que contém o script de backup do MySQL
     */
    public void restore(File fileToRestore) {
        try {
            String restorePath = fileToRestore.getPath();
            String[] executeCmd = null;

            if (JsfUtil.isDevelopmentProjectStage()) {
                executeCmd = new String[]{"c:/Program Files/MySQL/MySQL Server 5.6/bin/mysql.exe", dbName, "-u" + dbUser, "-p" + dbPass, "-e", " source " + restorePath};
            } else {
                executeCmd = new String[]{"mysql", dbName, "-u" + dbUser, "-p" + dbPass, "-e", " source " + restorePath};
//                executeCmd = new String[]{"mysql", "-u" + dbUser, "-p" + dbPass, dbName, " < " + restorePath};
            }
//            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            ProcessBuilder processBuilder = new ProcessBuilder(executeCmd);

            processBuilder.redirectErrorStream(true);

            Process runtimeProcess = processBuilder.start();

            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                addMessage("Backup restaurado com sucesso!", FacesMessage.SEVERITY_INFO);
            } else {
                addMessage("Erro ao restaurar backup!", FacesMessage.SEVERITY_ERROR);
            }
        } catch (IOException | InterruptedException e) {
            addMessage("Erro ao restaurar backup! " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
            e.printStackTrace();
        }
    }

    public void efetuarBkp() {
        try {
            String folderPath = PATH_BKP;

            File f1 = new File(folderPath);
            if (!f1.exists()) {
                f1.mkdir();
            }

            String savePath = folderPath + "/" + getFileName() + ".sql";

            String executeCmd = "";

            if (JsfUtil.isDevelopmentProjectStage()) {
                executeCmd = "c:/Program Files/MySQL/MySQL Server 5.6/bin/mysqldump.exe -u" + dbUser + " -p" + dbPass + " --database " + dbName + " -r " + savePath;
            } else {
                executeCmd = "mysqldump --single-transaction -u" + dbUser + " -p" + dbPass + " --database " + dbName + " -r " + savePath;
            }
            Process runtimeProcess = null;

            runtimeProcess = Runtime.getRuntime().exec(executeCmd);

            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                addMessage("Backup efetuado com sucesso!", FacesMessage.SEVERITY_INFO);
            } else {
                addMessage("Erro ao efetuar backup!", FacesMessage.SEVERITY_ERROR);
            }

        } catch (IOException | InterruptedException e) {
            addMessage("Erro ao efetuar backup!" + e.getMessage(), FacesMessage.SEVERITY_ERROR);
            e.printStackTrace();
        }
    }

    public void excluir(File file) {
        this.fileToExclude = file;
    }

    public void delete() {
        if (!this.fileToExclude.delete()) {
            addMessage("Erro ao tentar excluir arquivo de backup: " + this.fileToExclude.getName() , FacesMessage.SEVERITY_ERROR);
        } else {
            addMessage("Excluído arquivo de backup com sucesso: " + this.fileToExclude.getName() , FacesMessage.SEVERITY_INFO);
        }
    }

    private String getFileName() {
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmm");
        String s = f.format(Calendar.getInstance().getTime());
        return "backup_" + s;
    }

    public List<File> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<File> arquivos) {
        this.arquivos = arquivos;
    }

    public String getDataDoArquivo(String fileName) {
        try {
            String strData = fileName.substring(7, fileName.length() - 4);
            SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmm");
            Date date = f.parse(strData);
            f = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            return f.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}
