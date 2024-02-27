package br.com.sannicollas.relatorio;

import br.com.sannicollas.exception.SanNicollasException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.Locale;
import java.util.Map;

@Component
public class RelatorioService {

    private String imagesPath = "classpath:/reports/img/";

    private String reportsPath = "reports/";

    public String getImagesPath(String imageName) {
        return imagesPath + imageName;
    }

    public String getReportsPath(String reportName) {
        return reportsPath + reportName;
    }

    public byte[] gerarRelatorioJrxml(Map<String, Object> parametros, String relatorioPath) {
        try {
            parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

            Connection conn = RelatorioConexao.getConnection();
            conn.beginRequest();

            JasperReport compileManager = JasperCompileManager.compileReport(getClass().getClassLoader().getResourceAsStream(relatorioPath));
            JasperPrint print = JasperFillManager.fillReport(compileManager, parametros, conn);
            byte[] bytes = JasperExportManager.exportReportToPdf(print);

            conn.endRequest();

            return bytes;
        } catch (Exception e) {
            throw new SanNicollasException("Falha na geração do relatório");
        }
    }

}
