package newapp

import net.sf.jasperreports.engine.JRException
import net.sf.jasperreports.engine.JRExporter
import net.sf.jasperreports.engine.JRExporterParameter
import net.sf.jasperreports.engine.JasperCompileManager
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.JasperPrint
import net.sf.jasperreports.engine.export.JRPdfExporter
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource

import java.sql.*
import java.io.ByteArrayOutputStream

import newapp.model.Group
import newapp.model.ListGroup
import newapp.model.Data
import newapp.model.ListData
import newapp.model.Course
import newapp.model.ListCourse

class ReportController {

    def mailService

    def email() {
        ByteArrayOutputStream  bos = null
        byte[] b = null

        try {
            String filename = "group"
            String dotJasper = grailsApplication.mainContext.getResource('reports/' + filename + '.jasper').file.getAbsoluteFile()

            ListGroup o = new ListGroup()
            List<Group> l = o.getGroupList()
            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(l)

            JasperPrint print = JasperFillManager.fillReport(dotJasper, null, beanColDataSource)

            bos = new ByteArrayOutputStream()
            JRExporter exporter = new JRPdfExporter()
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print)
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, bos)
                
            exporter.exportReport()
            b = bos.toByteArray()
        }

        catch (Exception e) {
            throw e
        }

        finally {
            bos?.close()
        }

        mailService.sendMail {
            multipart true
            from "wfsiew@hotmail.com"
            to "siewwingfei@hotmail.com"
            subject "hello"
            html view: "email", model: [param1: "value1"]
            attach("data.pdf", "application/pdf", b)
        }

        render "success"
    }

    def index() { 
    	ByteArrayOutputStream  bos = null
    	byte[] b = null

    	try {
    		String filename = "Blank_A4"
    		//String reportname = grailsApplication.mainContext.getResource('reports/' + filename + '.jrxml').file.getAbsoluteFile()
    		String dotJasper = grailsApplication.mainContext.getResource('reports/' + filename + '.jasper').file.getAbsoluteFile()

    		//JasperCompileManager.compileReportToFile(reportname)
    		JasperPrint print = JasperFillManager.fillReport(dotJasper, null, getConnection())

    		bos = new ByteArrayOutputStream()
    		JRExporter exporter = new JRPdfExporter()
    		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print)
    		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, bos)
    		    
    		exporter.exportReport()
    		b = bos.toByteArray()
    	}

    	catch (Exception e) {
    		throw e
    	}

    	finally {
    		bos?.close()
    	}

    	render(file: b, contentType: 'application/pdf')
    }

    def group() {
        ByteArrayOutputStream  bos = null
        byte[] b = null

        try {
            String filename = "group"
            String dotJasper = grailsApplication.mainContext.getResource('reports/' + filename + '.jasper').file.getAbsoluteFile()

            ListGroup o = new ListGroup()
            List<Group> l = o.getGroupList()
            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(l)

            JasperPrint print = JasperFillManager.fillReport(dotJasper, null, beanColDataSource)

            bos = new ByteArrayOutputStream()
            JRExporter exporter = new JRPdfExporter()
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print)
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, bos)
                
            exporter.exportReport()
            b = bos.toByteArray()
        }

        catch (Exception e) {
            throw e
        }

        finally {
            bos?.close()
        }

        render(file: b, contentType: 'application/pdf')
    }

    def data() {
    	ByteArrayOutputStream  bos = null
        byte[] b = null

        try {
        	String filename = "data"
        	String dotJasper = grailsApplication.mainContext.getResource('reports/' + filename + '.jasper').file.getAbsoluteFile()

        	ListData o = new ListData()
        	List<Data> l = o.getDataList()
        	JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(l)

        	def map = [:]
        	map = [ReportTitle: "List of Contacts", Author: "Prepared By Manisha"]

        	JasperPrint print = JasperFillManager.fillReport(dotJasper, map, beanColDataSource)

        	bos = new ByteArrayOutputStream()
            JRExporter exporter = new JRPdfExporter()
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print)
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, bos)
                
            exporter.exportReport()
            b = bos.toByteArray()
        }

        catch (Exception e) {
            throw e
        }

        finally {
            bos?.close()
        }

        render(file: b, contentType: 'application/pdf')
    }

    def orders() {
        ByteArrayOutputStream  bos = null
        byte[] b = null

        try {
            String filename = "orders"
            String reportname = grailsApplication.mainContext.getResource('reports/' + filename + '.jrxml').file.getAbsoluteFile()
            String dotJasper = grailsApplication.mainContext.getResource('reports/' + filename + '.jasper').file.getAbsoluteFile()

            //JasperCompileManager.compileReportToFile(reportname)
            JasperPrint print = JasperFillManager.fillReport(dotJasper, null, getConnection2())

            bos = new ByteArrayOutputStream()
            JRExporter exporter = new JRPdfExporter()
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print)
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, bos)
                
            exporter.exportReport()
            b = bos.toByteArray()
        }

        catch (Exception e) {
            throw e
        }

        finally {
            bos?.close()
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
		
		con
    }

    private getConnection2() {
        Connection con  = null

        try {
            String constr = "jdbc:jtds:sqlserver://localhost/FibreSystem;user=sa;password=root;instance=mssqlserver14"

            Class.forName("net.sourceforge.jtds.jdbc.Driver")
            con = DriverManager.getConnection(constr)
        }

        catch (Exception e) {
            throw e
        }

        con
    }
}
