package service;

import model.Admin;

import java.util.Vector;

public class AdminService {
    public static boolean login(Admin u) throws Exception{
        Vector<Admin> list = null;
        try{
            list = u.findAll();
            for (int i = 0; i < list.size(); i++) {
                if(u.getNom().equals(list.get(i).getNom()) && u.getMdp().equals(list.get(i).getMdp()) ) {
                    return true;
                }
            }
            return false;
//            if(x==0) System.out.println("diso");
        }catch(Exception e){
            throw e;
        }
    }
}
