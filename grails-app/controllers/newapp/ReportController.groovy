package newapp

import net.sf.jasperreports.engine.JRException
import net.sf.jasperreports.engine.JRExporter
import net.sf.jasperreports.engine.JRExporterParameter
import net.sf.jasperreports.engine.JasperCompileManager
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.JasperPrint
import net.sf.jasperreports.engine.export.JRPdfExporter

import java.sql.*
import java.io.ByteArrayOutputStream

class ReportController {

    def index() { 
    	ByteArrayOutputStream  pdfStream = null
    	byte[] b = null

    	try {
    		String filename = "Blank_A4"
    		String reportname = grailsApplication.mainContext.getResource('reports/' + filename + '.jrxml').file.getAbsoluteFile()
    		String dotJasper = grailsApplication.mainContext.getResource('reports/' + filename + '.jasper').file.getAbsoluteFile()

    		JasperCompileManager.compileReportToFile(reportname)
    		JasperPrint print = JasperFillManager.fillReport(dotJasper, null, getConnection())

    		pdfStream = new ByteArrayOutputStream()
    		JRExporter exporter = new JRPdfExporter()
    		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print)
    		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, pdfStream)
    		    
    		exporter.exportReport()
    		b = pdfStream.toByteArray()
    	}

    	catch (Exception e) {
    		throw e
    	}

    	finally {
    		pdfStream?.close()
    	}

    	render(file: b, contentType: 'application/pdf')
    }

    private getConnection() {
		Connection con = null
		
		try {
			String constr = "jdbc:jtds:sqlserver://192.168.138.120:1433/HSBB_Billing;user=CallBilling;password=CBPWD12345"
			
			Class.forName("net.sourceforge.jtds.jdbc.Driver")
			con = DriverManager.getConnection(constr)
		}
		
		catch (Exception e) {
			throw e
		}
		
		return con	}
}