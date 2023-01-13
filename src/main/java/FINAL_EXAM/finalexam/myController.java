/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FINAL_EXAM.finalexam;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hp
 * 
 * Nama : Aldy Ahmad Fauzi
 * NIM : 20200140043
 * 
 */
@RestController 
@CrossOrigin
@RequestMapping("kudata")
public class myController {
    
    Surat data = new Surat();//membuat variabel baru untuk class surat
    SuratJpaController control = new SuratJpaController();//membuat variabel baru untuk class surat JPA
    
    @GetMapping
    public List<Surat> getAll()//membuat method untuk menampilkan semua data
    {
        List<Surat> dummy = new ArrayList<>();//menyimpan data surat kedalam variabel dummy List
        try{
            dummy = control.findSuratEntities();//menampilkan semua entitas yang ada dalam dummy List
        }catch(Exception e){
            dummy = List.of();
        }
        
        return dummy;
    }
    
    @PostMapping
    public String createData(HttpEntity<String> send)//membuat method createdata
    {
        String pesan = "";//membuat variabel string
        
        try{
            String json_receive = send.getBody();//menyimpan body kedalam json receive
            
            ObjectMapper mapper = new ObjectMapper();//membuat object mapper
            
            
            data = mapper.readValue(json_receive, Surat.class);//menyimpan mapper kedalam data
            
            control.create(data);//memnaggil fungsi create dan memasukan kedalam data
            
            pesan = data.getJudul() + " data surat tersimpan";//variabel pesan menyimpan data tersebut
        }catch(Exception e){//apa bila gagal
            pesan = "gagal";
        }
        return pesan;//mengembalikan nilai variabel pesan
    }
    
    @PutMapping
    public String editData(HttpEntity<String> send)//membuat method edit data
    {
        String pesan = "";//membuat variabel string
        
        try{
            String json_receive = send.getBody();//menyimpan body kedalam json receive  
            
            ObjectMapper mapper = new ObjectMapper();//membuat object mapper
            
            data = mapper.readValue(json_receive, Surat.class);//menyimpan mapper kedalam data
            
            control.edit(data);//memnaggil fungsi edit dan memasukan kedalam data
            
            pesan = data.getJudul() + " data surat terupdate";//variabel pesan menyimpan data tersebut
        }catch(Exception e){//apa bila gagal
            pesan = "gagal";
        }
        return pesan;//mengembalikan nilai variabel pesan
    }
    
    
    @DeleteMapping
    public String deleteData(HttpEntity<String> send)//membuat method delete
    {
        String pesan = "";//membuat variabel string
        
        try{
            String json_receive = send.getBody();//menyimpan body kedalam json receive
            
            ObjectMapper mapper = new ObjectMapper();//membuat object mapper
            
            data = mapper.readValue(json_receive, Surat.class);//menyimpan mapper kedalam data
            
            control.destroy(data.getId());//memanggil fungsi destroy kedalam data id
            
            pesan = "ID = " +data.getId() + " data surat terhapus";//menyimpan nilai string kedalam variabel pesan
        }catch(Exception e){
            pesan = "gagal";
        }
        return pesan;//mengembalikan nilai pesan
    }
    
}
