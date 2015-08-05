package newapp

import groovy.sql.Sql
import newapp.model.Customer
import newapp.model.Person

import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.CreationHelper
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook

class HomeController {

    def index() {
        render(view: "index")
    }

    def add() {
        def m = request.JSON
        println m
        String dt = m.dob
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd")

        Person o = new Person(name: m.name, dob: df.parse(m.dob))
        render(contentType: 'application/json') {
            o
        }
    }

    def data2() {
        def sql = null

        try {
            def db = [url:'jdbc:jtds:sqlserver://localhost:1433/appdb;instance=sqlexpress',
                      user:'sa', password:'root',
                      driver:'net.sourceforge.jtds.jdbc.Driver']
            sql = Sql.newInstance(db.url, db.user, db.password, db.driver)
            println sql
        }

        catch (Exception e) {
            e.printStackTrace()
        }

        finally {
            sql?.close()
        }

        render 'success'
    }

    def data() {
    	def sql = null
        List<Customer> l = []

    	try {
    		def db = [url:'jdbc:jtds:sqlserver://192.168.138.120:1433/HSBB_Billing',
            user:'CallBilling',
            password:'CBPWD12345',
            driver:'net.sourceforge.jtds.jdbc.Driver']
            sql = Sql.newInstance(db.url, db.user, db.password, db.driver)
            
            String q = """
                       select top 10 custid, name from customer order by creationdate desc
                       """

            sql.eachRow(q) { x -> 
                Customer o = new Customer(custID: x.custID, name: x.name)
                l.add(o)
            }
    	}

        catch (Exception e) {
            e.printStackTrace()
        }

        finally {
            sql?.close()
        }
    	
        render(contentType: 'application/json') {
            l
        }
    }

    def exfile() {
        byte[] b = null
        Workbook wb = null
        ByteArrayOutputStream bos = null

        try {
            wb = new XSSFWorkbook()
            Sheet sheet = wb.createSheet("sheet1")

            Row row = sheet.createRow(0)

            Cell cell = row.createCell(0)
            cell.setCellValue("Hello")

            bos = new ByteArrayOutputStream()
            wb.write(bos)
            b = bos.toByteArray()
        }

        catch(Exception e) {
            e.printStackTrace()
        }

        finally {
            bos?.close()
            wb?.close()
        }

        render(file: b, fileName: "file.xlsx", contentType: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    }
}
