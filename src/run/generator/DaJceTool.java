package run.generator;/*
 * 创建日期 2007-3-11
 *
 * Copyright: Copyright (c) 2007
 *
 * Company:   得安科技（DATECH）
 *
 * @author    满萌
 *
 * @version 1.0
 */
import java.io.*;
//import java.util.*;
//import java.security.*;
import javax.crypto.*;
//import javax.crypto.spec.*;
//import javax.crypto.Cipher;
import javax.crypto.SecretKey;

//import test.InputMasking;

//import test.MaskingThread;

import java.util.StringTokenizer;
import java.util.Vector;
//import javax.crypto.SecretKeyFactory;
//import javax.crypto.spec.DESKeySpec;
//import sun.misc.BASE64Encoder;
//import javax.crypto.spec.DESedeKeySpec;
//import java.security.spec.AlgorithmParameterSpec;
//import javax.crypto.spec.IvParameterSpec;

public class DaJceTool{
    static int workType = -1, rv = -1;
    static String path = null;
    BufferedReader keyIn,numIn;
    //	boolean flag = true11;//循环标记
    StringTokenizer st;//截取字符串的类
    Vector vec = new Vector();// 收取字段的集合
    String vip;
    static code show = new code();
    static String str =null;

    public static void main(String args[]){


        String line = new String();
        String tmp1 = new String();
        String Pass = new String();
        boolean passflag = false;
        boolean countflag = false;

        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.indexOf("windows") > -1)
        {
            String sysPath = System.getProperty("java.library.path");
            int index=sysPath.indexOf("system32");
            int start = sysPath.lastIndexOf(";",index);
            int end = sysPath.indexOf(";",index);
            path =sysPath.substring(start+1,end);
            str="\\";
        }
        else
        {
            path =System.getProperty("user.home");
            str="/";
        }

        //判断文件是否存在
        try{
            FileInputStream conf = new FileInputStream(path + str+"dtcrypt.ini");
            conf.close();
        }
        catch(IOException e){
//		    e.printStackTrace();
            System.out.println("File not found,now creating" + path + System.getProperty("file.separator") + "dtcrypt.ini ...");
            try{
                FileOutputStream newconf = new FileOutputStream(path + str+"dtcrypt.ini");
                PrintWriter Out = new PrintWriter(newconf);
                Out.println("DeviceCount = 0;");
                Out.println("SserverNum = 1;");
                Out.println("SserverIpAddresses = ;");
                Out.println("WaitIdleTimeOut = 300;");
                Out.println("SharedMemoryKey = 1111;");
                Out.println("CardLockKey = 2222;");
                Out.println("SharedMemorySize = 65537;");
                Out.println("MultiThreadSynchronizeKey = 4444;");
                Out.println("SharedMemoryLockKey = 5555;");
                Out.println("SemaphoreTimeOut = 300;");
                Out.println("SocketTimeOut = 160;");
                Out.println("ConnectCount = 5;");
                Out.println("PassWord = NzU3NzcyN2E3Njc5N2E2ODVkNWM0ZDVhNWM0ZDRiNDA=;");
                Out.println(".##");
                Out.close();
                newconf.close();
                System.out.println("File created successed!");
                passflag = true;
                countflag = true;
            }
            catch(IOException ee){
                ee.printStackTrace();
            }

        }

//		判断文件中是否存在ConnectCount项
        if(countflag == false){
            try{
                RandomAccessFile conf = new RandomAccessFile(path + str+"dtcrypt.ini","rw");
//				FileOutputStream OutFile = new FileOutputStream("/new.txt");
//				PrintWriter Out = new PrintWriter(OutFile);
                while((line = conf.readLine())!=null){
                    if(line.startsWith("ConnectCount")){
                        countflag = true;
                        break;
                    }
                    else{
                        countflag = false;
                    }
                }
                conf.close();

                if(countflag == false){
                    RandomAccessFile newconf = new RandomAccessFile(path + str+"dtcrypt.ini","rw");
                    FileOutputStream OutFile = new FileOutputStream(path + str+"new.txt");
                    PrintWriter Out = new PrintWriter(OutFile);
                    while((line = newconf.readLine())!=null){
                        if(line.startsWith("SocketTimeOut")){
                            Out.println(line);
                            Out.println("ConnectCount = 5;");
                        }
                        else{
                            Out.println(line);
                        }
                    }
                    newconf.close();
                    Out.close();
                    OutFile.close();
                    File dt = new File(path + str+"dtcrypt.ini");
                    dt.delete();
                    dt = new File(path + str+"new.txt");
                    dt.renameTo(new File(path + str+"dtcrypt.ini"));
                }

            }
            catch(IOException e){
                e.printStackTrace();
            }
        }

//		判断文件中是否存在PassWord项
        if(passflag == false){
            try{
                RandomAccessFile conf = new RandomAccessFile(path + str+"dtcrypt.ini","rw");
//				FileOutputStream OutFile = new FileOutputStream("/new.txt");
//				PrintWriter Out = new PrintWriter(OutFile);
                while((line = conf.readLine())!=null){
                    if(line.startsWith("PassWord")){
                        passflag = true;
                        break;
                    }
                    else{
                        passflag = false;
                    }
                }
                conf.close();

                if(passflag == false){
                    RandomAccessFile newconf = new RandomAccessFile(path + str+"dtcrypt.ini","rw");
                    FileOutputStream OutFile = new FileOutputStream(path + str+"new.txt");
                    PrintWriter Out = new PrintWriter(OutFile);
                    while((line = newconf.readLine())!=null){
                        if(line.startsWith("ConnectCount")){
                            Out.println(line);
                            Out.println("PassWord = ;");
                        }
                        else{
                            Out.println(line);
                        }
                    }
                    newconf.close();
                    Out.close();
                    OutFile.close();
                    File dt = new File(path + str+"dtcrypt.ini");
                    dt.delete();
                    dt = new File(path + str+"new.txt");
                    dt.renameTo(new File(path + str+"dtcrypt.ini"));
                }

            }
            catch(IOException e){
                e.printStackTrace();
            }
        }

        DaJceTool ct = new DaJceTool();
        while(workType != 0 || workType != 1 || workType != 2){
//			System.out.flush();
            ct.PrintWorks();
            ct.GetSelect();

            switch(workType){
                case 1:
                    rv = ct.ModiIP();
                    if(rv == 0)
                        show.GuiTemplate("Moidfy IP address successed!", 1);
//					    System.out.println("Moidfy IP address successed!");
                    else
                        show.GuiTemplate("Moidfy IP address error!", 2);
//					    System.out.println("Moidfy IP address error!");
                    break;
                case 2:
                    rv = ct.SetPassWord();
                    if(rv == 0)
                        show.GuiTemplate("Modify password successed!", 1);
//					    System.out.println("Modify password successed!");
                    else
                        show.GuiTemplate("Modify password error!", 2);
//					    System.out.println("Modify password error!");
                    break;
                case 3:
                    rv = ct.ModiConnectCount();
                    if(rv == 0)
                        show.GuiTemplate("Modify ConnectCount successed!", 1);
//					    System.out.println("Modify ConnectCount successed!");
                    else
                        show.GuiTemplate("Modify ConnectCount error!", 2);
//					    System.out.println("Modify ConnectCount error!");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Input error! Please input again!");
                    break;
            }
            if(workType == 0){
                break;
            }
        }
    }

    void PrintWorks()
    {
/*		System.out.println("*************************************************************");
		System.out.println("*                    DAtechCrypto JCE Tool                  *");
		System.out.println("*************************************************************");
		System.out.println("                                                             ");
		System.out.println("1.Modify IP Address             2.Modify Application Password");
		System.out.println("3.Modify ConnectCount");
		System.out.println("0.Exit");
		System.out.print("Select:");      */

/*	    System.out.println("                :::::::::::::::::::::::::::::::");
	    System.out.println("                ::         Welcome To        ::");
	    System.out.println("::::::::::::::::::        DAtechCrypto       ::::::::::::::::::::");
	    System.out.println("::              ::          JCE Tool         ::                ::");
	    System.out.println("::              :::::::::::::::::::::::::::::::                ::");
	    System.out.println("::                                                             ::");
	    System.out.println("::   1.Modify IP Address       2.Modify Application Password   ::");
	    System.out.println("::   3.Modify ConnectCount     0.Exit                          ::");
	    System.out.println("::                                                             ::");
	    System.out.println("::       :::::::::::::::::::::::::::::::::::::::::::::::       ::");
	    System.out.println(":::::::::::       Copyright: Datech technology        :::::::::::");
	    System.out.println("  :::::::::               Version:   1.0              :::::::::  ");
	    System.out.println("         :::::::::::::::::::::::::::::::::::::::::::::::         ");
	    System.out.print  ("     Select:");*/

        System.out.println("                 :::::::::::::::::::::::::::::::                 ");
        System.out.println("                 :: ******                    ::                 ");
        System.out.println("                 :: **   ***    Welcome To    ::                 ");
        System.out.println("    ::::::::::::::: ****  ***                 :::::::::::::::    ");
        System.out.println("  ::::::::::::::::: ***** ***  DAtechCrypto   :::::::::::::::::  ");
        System.out.println("::::::::::::::::::: ****  ***                 :::::::::::::::::::");
        System.out.println("::               :: **   ***     JCE Tool     ::               ::");
        System.out.println("::               :: ******                    ::               ::");
        System.out.println("::               :::::::::::::::::::::::::::::::               ::");
        System.out.println("::                                                             ::");
        System.out.println("::   1.Modify IP Address       2.Modify Application Password   ::");
        System.out.println("::   3.Modify ConnectCount     0.Exit                          ::");
        System.out.println("::                                                             ::");
        System.out.println("::       :::::::::::::::::::::::::::::::::::::::::::::::       ::");
        System.out.println(":::::::::::        Copyright: Datech technology       :::::::::::");
        System.out.println("     ::::::               Version:   1.0              ::::::     ");
        System.out.println("         :::::::::::::::::::::::::::::::::::::::::::::::         ");
        System.out.print  ("     Select:");
    }

    void GetSelect(){
        workType = -1;
//		if(workType<0 || workType>2)
//		{
        try
        {
            keyIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.flush();
            workType = Integer.parseInt(keyIn.readLine());
        }
        catch (Exception e){}
        //   	}
    }

    int ModiIP(){
        int num = -1;
        int rv = -1;
        try{
            //读取并显示SserverIpAddresses默认值
            //String path = System.getProperty("user.home");
            RandomAccessFile defconf = new RandomAccessFile(path + str+"dtcrypt.ini", "r");
            String defline = new String();
            while((defline = defconf.readLine()) != null){
                if(defline.startsWith("SserverIpAddresses")){
                    System.out.println("Current: " + defline);
                    defconf.close();
                    break;
                }
            }

            while(num <1 || num > 4){
                System.out.print("Please input amount of IP(1~4): ");
                numIn = new BufferedReader(new InputStreamReader(System.in));
                num = Integer.parseInt(numIn.readLine());
                if(num < 1 || num > 4){
                    System.out.println("Input error!");
                }
            }
        }
        catch(Exception e){
//    		e.printStackTrace();
            System.out.println("Input error!");
            return -1;

        }

        switch(num){
            case 1:
                rv = this.OneIP();
                if(rv != 0){
                    return rv;
                }
                break;
            case 2:
                rv = this.TwoIP();
                if(rv != 0){
                    return rv;
                }
                break;
            case 3:
                rv = this.ThreeIP();
                if(rv != 0){
                    return rv;
                }
                break;
            case 4:
                rv = this.FourIP();
                if(rv != 0){
                    return rv;
                }
                break;
            default:
                break;
        }
        return 0;
    }

    int SetPassWord(){
        InputStreamReader Input = new InputStreamReader(System.in);
        BufferedReader InputBuf = new BufferedReader(Input);
        String myline = new String();
        code masking = new code();

        try{
            System.out.println("Please input password(6--20): ");
            System.out.flush();
            myline = masking.getPassword("");
//			myline = InputBuf.readLine();
            if(myline.length() <6 ||myline.length() > 20){
                System.out.println("Password length error!(6--20)");
                return -1;
            }
        }
        catch(IOException e){
            System.out.println("Input error");
//			e.printStackTrace();
        }

        ////////////////////////////////PackKey/////////////////////////////////////
//		System.out.println("Password is: "+myline);
        code Passwd = new code();
        byte PKey[] = Passwd.PackKey(myline.getBytes());
//		System.out.println("PackKey is:" + PKey);
        ////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////EncKey//////////////////////////////////////
//		System.out.println("Pkey Length: "+PKey.length);
        byte[] EncKey = Passwd.EncKey(PKey);
//		System.out.println("EncKey Length: "+EncKey.length);
//		System.out.println("EncKey: "+EncKey);
//		byte[] EncodeKey = Passwd.Data_Bin2Txt(EncKey, EncKey.length);
//		System.out.println("EncodeKey Length: "+EncodeKey.length);
//		System.out.println("encode key: "+EncodeKey);
        ////////////////////////////////////////////////////////////////////////////

        /////////////////////////WritePasswordToConfig//////////////////////////////
        Passwd.WritePasswordToConfig(EncKey,path,str);
        ////////////////////////////////////////////////////////////////////////////
        return 0;
    }

    int ModiConnectCount(){
        String line = new String();
        String tmp1 = new String();
        String Input = new String();
        int num = -1;

        try{
            //  String path = System.getProperty("user.home");

            //读取并显示ConnectCount默认值
            RandomAccessFile defconf = new RandomAccessFile(path + str+"dtcrypt.ini", "r");
            String defline = new String();
            while((defline = defconf.readLine()) != null){
                if(defline.startsWith("ConnectCount")){
                    System.out.println("Current: " + defline);
                    defconf.close();
                    break;
                }
            }

            RandomAccessFile conf = new RandomAccessFile(path + str+"dtcrypt.ini", "rw");
            BufferedReader CIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Input num of ConnectCount(1--300): ");
            System.out.flush();
            Input = CIn.readLine();

            try{
                num = Integer.parseInt(Input);
            }
            catch(Exception e){
                System.out.println("Input error!");
                conf.close();
                return -1;
            }

            if(num <1 || num > 300){
                System.out.println("Input error!");
                conf.close();
                return -1;
            }

            FileOutputStream OutFile = new FileOutputStream(path +str+"new.txt");
            PrintWriter Out = new PrintWriter(OutFile);
            while((line = conf.readLine())!=null){
                if(line.startsWith("ConnectCount")){
                    tmp1 = "ConnectCount = "+Input+";";

                    System.out.println(tmp1);
                    Out.println(tmp1);
                }
                else{
//					System.out.println(line);
                    Out.println(line);
                }
            }
            conf.close();
            Out.close();
            OutFile.close();
            File dt = new File(path + str+"dtcrypt.ini");
            dt.delete();
            dt = new File(path + str+"new.txt");
            dt.renameTo(new File(path + str+"dtcrypt.ini"));
        }
        catch(Exception e){
//			System.out.println("Input error!");
            return -1;
        }

        return 0;
    }

    int OneIP(){
        int rv = -1;
        String line = new String();
        String tmp1 = new String();
        String Input1 = new String();

        try{
            //String path = System.getProperty("user.home");
            RandomAccessFile conf = new RandomAccessFile(path + str+"dtcrypt.ini", "rw");
            BufferedReader IPIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Input IP address: ");
            System.out.flush();
            Input1 = IPIn.readLine();
            Input1 = this.ValiIp(Input1);
            if(Input1 == null){
//			    System.out.println("rv = " + rv);
                conf.close();
                return -1;
            }
            FileOutputStream OutFile = new FileOutputStream(path + str+"new.txt");
            PrintWriter Out = new PrintWriter(OutFile);
            while((line = conf.readLine())!=null){
                if(line.startsWith("SserverIpAddresses")){
                    tmp1 = "SserverIpAddresses = "+Input1+";";

                    System.out.println(tmp1);
                    Out.println(tmp1);
                }
                else{
//					System.out.println(line);
                    Out.println(line);
                }
            }
            conf.close();
            Out.close();
            OutFile.close();
            File dt = new File(path +str+"dtcrypt.ini");
            dt.delete();
            dt = new File(path +str+"new.txt");
            if(dt.renameTo(new File(path +str+"dtcrypt.ini")) == false)
                System.out.println("rename error!");
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return 0;
    }

    int TwoIP(){
        String line = new String();
        String tmp1 = new String();
        String Input1 = new String();
        String Input2 = new String();

        try{
            //	String path = System.getProperty("user.home");
            RandomAccessFile conf = new RandomAccessFile(path +str+"dtcrypt.ini", "rw");
            BufferedReader IPIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.flush();
            System.out.print("Input 1st IP address: ");
            Input1 = IPIn.readLine();
            Input1 = this.ValiIp(Input1);
            if(Input1 == null){
                conf.close();
                return -1;
            }
            System.out.print("Input 2nd IP address: ");
            Input2 = IPIn.readLine();
            Input2 = this.ValiIp(Input2);
            if(Input2 == null){
                conf.close();
                return -1;
            }
            FileOutputStream OutFile = new FileOutputStream(path +str+"new.txt");
            PrintWriter Out = new PrintWriter(OutFile);
            while((line = conf.readLine())!=null){
                if(line.startsWith("SserverIpAddresses")){
                    tmp1 = "SserverIpAddresses = "+Input1+","+Input2+";";
                    System.out.println(tmp1);
                    Out.println(tmp1);
                }
                else{
                    Out.println(line);
                }
            }
            conf.close();
            Out.close();
            OutFile.close();
            File dt = new File(path +str+"dtcrypt.ini");
            dt.delete();
            dt = new File(path +str+"new.txt");
            dt.renameTo(new File(path +str+"dtcrypt.ini"));
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return 0;
    }

    int ThreeIP(){
        String line = new String();
        String tmp1 = new String();
        String Input1 = new String();
        String Input2 = new String();
        String Input3 = new String();

        try{
            //String path = System.getProperty("user.home");
            RandomAccessFile conf = new RandomAccessFile(path +str+"dtcrypt.ini", "rw");
            BufferedReader IPIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.flush();
            System.out.print("Input 1st IP address: ");
            Input1 = IPIn.readLine();
            Input1 = this.ValiIp(Input1);
            if(Input1 == null){
                conf.close();
                return -1;
            }
            System.out.print("Input 2nd IP address: ");
            Input2 = IPIn.readLine();
            Input2 = this.ValiIp(Input2);
            if(Input2 == null){
                conf.close();
                return -1;
            }
            System.out.print("Input 3rd IP address: ");
            Input3 = IPIn.readLine();
            Input3 = this.ValiIp(Input3);
            if(Input3 == null){
                conf.close();
                return -1;
            }
            FileOutputStream OutFile = new FileOutputStream(path +str+"new.txt");
            PrintWriter Out = new PrintWriter(OutFile);
            while((line = conf.readLine())!=null){
                if(line.startsWith("SserverIpAddresses")){
                    tmp1 = "SserverIpAddresses = "+Input1+","+Input2+","+Input3+";";
                    System.out.println(tmp1);
                    Out.println(tmp1);
                }
                else{
//					System.out.println(line);
                    Out.println(line);
                }
            }
            conf.close();
            Out.close();
            OutFile.close();
            File dt = new File(path +str+"dtcrypt.ini");
            dt.delete();
            dt = new File(path +str+"new.txt");
            dt.renameTo(new File(path +str+"dtcrypt.ini"));
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return 0;
    }
    int FourIP(){
        String line = new String();
        String tmp1 = new String();
        String Input1 = new String();
        String Input2 = new String();
        String Input3 = new String();
        String Input4 = new String();

        try{
            //String path = System.getProperty("user.home");
            RandomAccessFile conf = new RandomAccessFile(path +str+"dtcrypt.ini", "rw");
            BufferedReader IPIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.flush();
            System.out.print("Input 1st IP address: ");
            Input1 = IPIn.readLine();
            Input1 = this.ValiIp(Input1);
            if(Input1 == null){
                conf.close();
                return -1;
            }
            System.out.print("Input 2nd IP address: ");
            Input2 = IPIn.readLine();
            Input2 = this.ValiIp(Input2);
            if(Input2 == null){
                conf.close();
                return -1;
            }
            System.out.print("Input 3rd IP address: ");
            Input3 = IPIn.readLine();
            Input3 = this.ValiIp(Input3);
            if(Input3 == null){
                conf.close();
                return -1;
            }
            System.out.print("Input 4rd IP address: ");
            Input4 = IPIn.readLine();
            Input4 = this.ValiIp(Input4);
            if(Input4 == null){
                conf.close();
                return -1;
            }
            FileOutputStream OutFile = new FileOutputStream(path +str+"new.txt");
            PrintWriter Out = new PrintWriter(OutFile);
            while((line = conf.readLine())!=null){
                if(line.startsWith("SserverIpAddresses")){
                    tmp1 = "SserverIpAddresses = "+Input1+","+Input2+","+Input3+","+Input4+";";
                    System.out.println(tmp1);
                    Out.println(tmp1);
                }
                else{
//					System.out.println(line);
                    Out.println(line);
                }
            }
            conf.close();
            Out.close();
            OutFile.close();
            File dt = new File(path +str+"dtcrypt.ini");
            dt.delete();
            dt = new File(path +str+"new.txt");
            dt.renameTo(new File(path +str+"dtcrypt.ini"));
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return 0;
    }
    String ValiIp(String ip){
        st=new StringTokenizer(ip);
        vip=ip;
//		flag = true;
        String newIp = "";


        // 验证第一个和最后一个是不是"."
        if (vip.charAt(0)!='.' && vip.charAt(vip.length()-1)!='.'){

            // 循环取出IP中的数字
            while (st.hasMoreElements()){
                vec.add(st.nextToken("."));
            }

            //验证是否为4段数字
            if (vec.size()==4){
                // 循环验证4个数字
                for ( int i=0;i<4;i++) {
                    // 从集合中取出元素
                    String dd = String.valueOf(vec.elementAt(i));

                    //验证每个数字是否为3位数字以内
                    if(dd.length() > 3){
//					    flag = false;
                        vec.clear();
                        System.out.println("Invalid input! The each number must be less than three-figure!");
                        return null;
                    }

                    //强制转换为int
                    try {
                        int ii = Integer.parseInt(dd);
                        //					System.out.println(String.valueOf(ii));
                        if(i == 0 || i == 3){
                            if(ii == 0){
//						        flag = false;
                                vec.clear();
                                System.out.println("Invalid input! The first or last number can not be 0!");
                                return null;
                            }
                        }
                        // 验证是否在0-255之间
                        if (ii>255||ii<0) {
//							flag = false;
                            vec.clear();
                            System.out.println("Invalid input! Input number over 255!");
                            return null;
                        }

                        //重组IP地址,如果高位为0则将0去掉
                        if(i != 3){
                            newIp += String.valueOf(ii);
                            newIp += ".";
                        }
                        else{
                            newIp += String.valueOf(ii);
                        }
                    }
                    //转换出错抛异常(比如有字母)
                    catch (Exception e){
//						flag = false;
                        vec.clear();
                        System.out.println("Invalid input! Input may be have a word!");
                        return null;
                    }
                }

            }
            else {
//				flag = false;
                vec.clear();
                System.out.println("Invalid input! Input length error!");
                return null;
            }
        }
        else {
//			flag = false ;
            vec.clear();
            System.out.println("Invalid input! Input format error!");
            return null;
        }
        vec.clear();
        return newIp;

    }
}

class code {

    private byte[] sessionkey;
    private int skeylen;

    protected final byte[] encodingTable =
            {
                    (byte)'A', (byte)'B', (byte)'C', (byte)'D', (byte)'E', (byte)'F', (byte)'G',
                    (byte)'H', (byte)'I', (byte)'J', (byte)'K', (byte)'L', (byte)'M', (byte)'N',
                    (byte)'O', (byte)'P', (byte)'Q', (byte)'R', (byte)'S', (byte)'T', (byte)'U',
                    (byte)'V', (byte)'W', (byte)'X', (byte)'Y', (byte)'Z',
                    (byte)'a', (byte)'b', (byte)'c', (byte)'d', (byte)'e', (byte)'f', (byte)'g',
                    (byte)'h', (byte)'i', (byte)'j', (byte)'k', (byte)'l', (byte)'m', (byte)'n',
                    (byte)'o', (byte)'p', (byte)'q', (byte)'r', (byte)'s', (byte)'t', (byte)'u',
                    (byte)'v',
                    (byte)'w', (byte)'x', (byte)'y', (byte)'z',
                    (byte)'0', (byte)'1', (byte)'2', (byte)'3', (byte)'4', (byte)'5', (byte)'6',
                    (byte)'7', (byte)'8', (byte)'9',
                    (byte)'+', (byte)'/'
            };

    protected byte    padding = (byte)'=';

    /*
     * set up the decoding table.
     */
    protected final byte[] decodingTable = new byte[128];

    static byte[] intToBytes(int x){
        byte[] rv = new byte[4];

        Integer xo0 = new Integer(x);
        Integer xo1 = new Integer(x/256);

        rv[3] = 0x00;
        rv[2] = 0x00;
        rv[1] = xo1.byteValue();
        rv[0] = xo0.byteValue();

        return rv;

    }

    byte[] PackKey(byte[] Password)
    {
        byte[] OutKey=null;
        int keyLen;
        byte[]  outdata=null;

//		System.out.println("Password.length: "+Password.length);
        if((outdata=GenHash(Password,Password.length))==null)
            return null;
//		System.out.println("GenHash length: "+outdata.length);
        if((OutKey=HexToAsc(outdata,outdata.length))==null)
            return null;
        keyLen = outdata.length*2;
//		System.out.println("HexToAsc length: "+OutKey.length);

        return OutKey;
    }

    byte[] GenHash(byte[] indata, int insize)
    {
        int i, j,length =insize;
        int padsize, blocks;
        byte msg[]=new byte[16];
        byte data[]=new byte [4096];
        String str = "DEANCOMPUTERTECH";
        byte outdata[] = new byte[16];
        int outsize;

        data=addPad(indata, insize);
        blocks = data.length/16;
        //memcpy(msg, data, 16);
        System.arraycopy(data,0,msg,0,16);

        for(i = 1; i < blocks; i ++)
        {
            for (j = 0; j < 16; j ++){
                //msg[j] = msg[j] ^ data[i*16+j];
                int content1 = msg[j] ^ data[i*16+j];
                Integer m1 =new Integer(content1);
                msg[j]=m1.byteValue();

            }
        }
        for (j = 0; j < 16; j ++){
            //msg[j] = msg[j] ^ str[j];
            int content2 = msg[j] ^ str.charAt(j);
            Integer m2 =new Integer(content2);
            msg[j]=m2.byteValue();
        }

        //memcpy(outdata, msg, 16);
        System.arraycopy(msg,0,outdata,0,16);
        outsize = 16;
        return outdata;
    }

    byte[] addPad(byte []data,	int  insize)
    {
        int pad_bytes,leftbytes;
        int tail_bytes;
        byte[] lastbytes=new byte[20];
        int cfb_block = 16;
        int indatablk;
        int i;
        byte outdata[] = new byte[40];

        indatablk = (insize+(cfb_block-1))/cfb_block;
        pad_bytes = cfb_block - (insize % cfb_block);
        if (pad_bytes==cfb_block)
            indatablk++;
        leftbytes = 16-pad_bytes;
        tail_bytes = insize - leftbytes;
        //memcpy(lastbytes,&data[tail_bytes],leftbytes);
        //dest,souce
        System.arraycopy(data,tail_bytes,lastbytes,0,leftbytes);
        Integer m =new Integer(pad_bytes);
        for (i=0;i<pad_bytes;i++)
            lastbytes[15-i]=m.byteValue();
        //System.arraycopy(la,0,outdata,0,16);
        //memcpy(outdata,data,tail_bytes);
        //memcpy(outdata+tail_bytes,lastbytes,16);
//		System.out.println("1.error!");
        System.arraycopy(data,0,outdata,0,tail_bytes);
//		System.out.println("2.error!");
        System.arraycopy(lastbytes,0,outdata,tail_bytes,16);
//		System.out.println("3/error!");
        return outdata;
    }

    byte[] HexToAsc( byte[] sour ,int inlen)
    {
        int i;
        int n;
        byte []dest=new byte[inlen*2];
        for(i=0;i<inlen*2;i++) {
            if (i%2 != 0)
                n = sour[i/2] & 0x0f;
            else
                n = (sour[i/2]>>4) & 0x0f;
            if (n<10)
            //dest[i] = intToByte('0' + n);
            {
                int content='0' + n;
                byte[] data=intToBytes(content);
                System.arraycopy(data,0,dest,i,1);
            }
            else
            {
                byte[] data=intToBytes('a'+(n-10));
                System.arraycopy(data,0,dest,i,1);
                //dest[i] = 'a'+(n-10);
            }
        }//end for
        return dest;
    }

    /**************************************************************
     FUNCTION DECLARE  : convert binary to txt
     PARAMETER DECLARE :
     IN:  binData   	binary data
     binDataLen	binary data lenth
     OUT: txtData	txt data
     txtDataLen txt data lenth
     RETURN CODE :
     1    success
     ***************************************************************/
    byte[] Data_Bin2Txt(byte[] binData,int binDataLen){
        int i,k;
        byte t;
//	    byte  []  txtData=null;
        int txtDataLen;

        txtDataLen = (binDataLen << 1);
        byte  []  txtData=new byte[txtDataLen];

        k = 0;
        for(i=0;i<binDataLen;i++){
            int m=(binData[i] >> 4);
            Integer m1=new Integer(m);
            t = m1.byteValue();

            if(t<10){
                //txtData[k++] = t + '0';
                int n=t + '0';
                Integer n1=new Integer(n);
                txtData[k++] = n1.byteValue();
            }
            else{
                //txtData[k++] = t - 10 + 'A';
                int n=t - 10 + 'A';
                Integer n1=new Integer(n);
                txtData[k++] = n1.byteValue();
            }

            //t = binData[i] & 0x0F;
            int p=binData[i] & 0x0F;
            Integer p1=new Integer(p);
            t = p1.byteValue();
            if(t<10){
                //txtData[k++] = t + '0';
                int a=t + '0';
                Integer a1=new Integer(a);
                txtData[k++] = a1.byteValue();
            }
            else{
                // txtData[k++] = t - 10 + 'A';
                int a=t - 10 + 'A';
                Integer a1=new Integer(a);
                txtData[k++] = a1.byteValue();
            }
        }

        return txtData;
    }


    /**************************************************************
     FUNCTION DECLARE  : convert txt to binary
     PARAMETER DECLARE :
     IN: txtData	txt data
     txtDataLen  txt data lenth
     OUT: binData   	binary data
     binDataLen	binary data lenth
     RETURN CODE :
     1  success
     -1  error
     ***************************************************************/

    byte [] Data_Txt2Bin(byte [] txtData, int txtDataLen){
        int i,k;
        byte  t;
        byte [] binData=null;
        int binDataLen;


        if(txtDataLen % 2!=0){
            return null;
        }

        binDataLen = (txtDataLen >> 1);

        k = 0;
        for(i=0;i<(binDataLen);i++){
            t = 0;
            if(txtData[k] < 'A'){
                int  n=txtData[k] - '0';
                Integer n1=new Integer (n);
                t= n1.byteValue();
                t <<=4;

            }
            else{
                //t = (txtData[k] - 'A' + 10);
                int  n=txtData[k]- 'A'+10;
                Integer n1=new Integer (n);
                t = n1.byteValue();
                t <<=4;
            }

            k++;

            if(txtData[k] < 'A'){
                //t += (txtData[k] - '0');
                int  n=txtData[k] - '0';
                Integer n1=new Integer (n);
                t+= n1.byteValue();
            }
            else{
                //t += (txtData[k] - 'A' + 10);
                int  n=txtData[k] - 'A'+10;
                Integer n1=new Integer (n);
                t+= n1.byteValue();
            }

            k++;

            binData[i] = t;
        }
        return binData;
    }


    byte[] EncKey(byte[] PKey){
        byte[] OutKey = null;
        byte[] EncKey = null;

        try{
            String keystring= "12345678";
            code sessionkeyraw=new code();
            sessionkeyraw.SessionKeyRaw(keystring.getBytes());
            sessionkeyraw.LoadSessionKey(keystring.getBytes());
            EncKey=sessionkeyraw.Encrypt(PKey);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
//		System.out.println("DesEncKey is:" + OutKey);

//		EncKey = Data_Bin2Txt(OutKey, OutKey.length);
//		EncKey = OutKey.toString();
        return EncKey;
    }

    int WritePasswordToConfig(byte[] Password,String path,String str){
        String line = new String();
        String tmp1 = new String();
        String Pass = new String();
        try{
            //String path = System.getProperty("user.home");
            RandomAccessFile conf = new RandomAccessFile(path +str+"dtcrypt.ini","rw");
            FileOutputStream OutFile = new FileOutputStream(path +str+"new.txt");
            PrintWriter Out = new PrintWriter(OutFile);
            while((line = conf.readLine())!=null){
                if(line.startsWith("PassWord")){
//					tmp1 = "PassWord = "+Password+";";
//					System.out.println(tmp1);
                    Out.print("PassWord = ");
                    for(int i = 0;i < Password.length; i++){
                        Out.print((char)Password[i]);
                    }
                    Out.println(";");
                }
                else{
//					System.out.println(line);
                    Out.println(line);
                }

            }
            conf.close();
            Out.close();
            OutFile.close();
            File dt = new File(path +str+"dtcrypt.ini");
            dt.delete();
            dt = new File(path +str+"new.txt");
            dt.renameTo(new File(path +str+"dtcrypt.ini"));
        }
        catch(IOException e){
            e.printStackTrace();
            return -1;
        }

        return 0;
    }

    void SessionKeyRaw()
    {
        sessionkey=null;
        skeylen = 0;
    }

    void SessionKeyRaw(byte[] key)
    {
        sessionkey = new byte[key.length];
        System.arraycopy(key,0,sessionkey,0,key.length);
    }

    void LoadSessionKey(byte[] key)
    {
        //System.out.println(skeylen);
        System.arraycopy(key,0,sessionkey,0,key.length);
    }

    byte[] Encrypt(byte[] indata) throws Exception
    {
/*		SecretKeySpec k = new SecretKeySpec(sessionkey, "DES");
	    Cipher cp = Cipher.getInstance("DES/ECB/Nopadding");
		cp.init(Cipher.ENCRYPT_MODE, k);
		byte ctext[] = cp.doFinal(indata);
		return ctext;	*/
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        base64_encode(indata, 0, indata.length, out);
        byte[] ctext = out.toByteArray();
        out.close();
        return ctext;
        // return indata;
    }

    /*	byte[] Decrypt(byte[] indata) throws Exception
        {
            SecretKeySpec k = new SecretKeySpec(sessionkey, "DES");
            Cipher cp = Cipher.getInstance("DES/ECB/Nopadding");
            cp.init(Cipher.DECRYPT_MODE, k);
            byte ptext[] = cp.doFinal(indata);
            return ptext;
        }
    */
    byte[] GenKey() throws Exception
    {
        KeyGenerator kg = KeyGenerator.getInstance("DES");
        kg.init(56);
        SecretKey k = kg.generateKey();
        byte[] kb = k.getEncoded();
        return kb;
    }

    void GuiTemplate(String Prompt, int Type){

        switch(Type){
            case 1:
                System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                System.out.println("                          Successed !                           ");
                System.out.println();
                System.out.println("                  " + Prompt);
                System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                break;
            case 2:
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("                            Error !                             ");
                System.out.println();
                System.out.println("                  " + Prompt);
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                break;
        }
    }

    String getPassword(String initial) throws IOException{
        MaskingThread listeningthread = new MaskingThread(initial);
        Thread thread_instance = new Thread(listeningthread);
        String password = "";
        thread_instance.start();
        while (true){
            char input = (char)System.in.read();
            listeningthread.stopMasking();
            if (input == '\r'){
                input = (char)System.in.read();
                if (input == '\n')
                    break;
                else
                    continue;
            }
            else if(input == '\n')
                break;
            else
                password += input;
        }
        return password;
    }

    protected void initialiseDecodingTable()
    {
        for (int i = 0; i < encodingTable.length; i++)
        {
            decodingTable[encodingTable[i]] = (byte)i;
        }
    }

    public code()
    {
        initialiseDecodingTable();
    }

    /**
     * encode the input data producing a base 64 output stream.
     *
     * @return the number of bytes produced.
     */
    public int base64_encode(
            byte[]                data,
            int                    off,
            int                    length,
            OutputStream    out)
            throws IOException
    {
        int modulus = length % 3;
        int dataLength = (length - modulus);
        int a1, a2, a3;

        for (int i = off; i < off + dataLength; i += 3)
        {
            a1 = data[i] & 0xff;
            a2 = data[i + 1] & 0xff;
            a3 = data[i + 2] & 0xff;

            out.write(encodingTable[(a1 >>> 2) & 0x3f]);
            out.write(encodingTable[((a1 << 4) | (a2 >>> 4)) & 0x3f]);
            out.write(encodingTable[((a2 << 2) | (a3 >>> 6)) & 0x3f]);
            out.write(encodingTable[a3 & 0x3f]);
        }

        /*
         * process the tail end.
         */
        int    b1, b2, b3;
        int    d1, d2;

        switch (modulus)
        {
            case 0:        /* nothing left to do */
                break;
            case 1:
                d1 = data[off + dataLength] & 0xff;
                b1 = (d1 >>> 2) & 0x3f;
                b2 = (d1 << 4) & 0x3f;

                out.write(encodingTable[b1]);
                out.write(encodingTable[b2]);
                out.write(padding);
                out.write(padding);
                break;
            case 2:
                d1 = data[off + dataLength] & 0xff;
                d2 = data[off + dataLength + 1] & 0xff;

                b1 = (d1 >>> 2) & 0x3f;
                b2 = ((d1 << 4) | (d2 >>> 4)) & 0x3f;
                b3 = (d2 << 2) & 0x3f;

                out.write(encodingTable[b1]);
                out.write(encodingTable[b2]);
                out.write(encodingTable[b3]);
                out.write(padding);
                break;
        }

        return (dataLength / 3) * 4 + ((modulus == 0) ? 0 : 4);
    }

    private boolean ignore(
            char    c)
    {
        return (c == '\n' || c =='\r' || c == '\t' || c == ' ');
    }

    /**
     * decode the base 64 encoded byte data writing it to the given output stream,
     * whitespace characters will be ignored.
     *
     * @return the number of bytes produced.
     */
    public int base64_decode(
            byte[]                data,
            int                    off,
            int                    length,
            OutputStream    out)
            throws IOException
    {
        byte[]    bytes;
        byte    b1, b2, b3, b4;
        int        outLen = 0;

        int        end = off + length;

        while (end > 0)
        {
            if (!ignore((char)data[end - 1]))
            {
                break;
            }

            end--;
        }

        int  i = off;
        int  finish = end - 4;

        while (i < finish)
        {
            while ((i < finish) && ignore((char)data[i]))
            {
                i++;
            }

            b1 = decodingTable[data[i++]];

            while ((i < finish) && ignore((char)data[i]))
            {
                i++;
            }

            b2 = decodingTable[data[i++]];

            while ((i < finish) && ignore((char)data[i]))
            {
                i++;
            }

            b3 = decodingTable[data[i++]];

            while ((i < finish) && ignore((char)data[i]))
            {
                i++;
            }

            b4 = decodingTable[data[i++]];

            out.write((b1 << 2) | (b2 >> 4));
            out.write((b2 << 4) | (b3 >> 2));
            out.write((b3 << 6) | b4);

            outLen += 3;
        }

        if (data[end - 2] == padding)
        {
            b1 = decodingTable[data[end - 4]];
            b2 = decodingTable[data[end - 3]];

            out.write((b1 << 2) | (b2 >> 4));

            outLen += 1;
        }
        else if (data[end - 1] == padding)
        {
            b1 = decodingTable[data[end - 4]];
            b2 = decodingTable[data[end - 3]];
            b3 = decodingTable[data[end - 2]];

            out.write((b1 << 2) | (b2 >> 4));
            out.write((b2 << 4) | (b3 >> 2));

            outLen += 2;
        }
        else
        {
            b1 = decodingTable[data[end - 4]];
            b2 = decodingTable[data[end - 3]];
            b3 = decodingTable[data[end - 2]];
            b4 = decodingTable[data[end - 1]];

            out.write((b1 << 2) | (b2 >> 4));
            out.write((b2 << 4) | (b3 >> 2));
            out.write((b3 << 6) | b4);

            outLen += 3;
        }

        return outLen;
    }

    /**
     * decode the base 64 encoded String data writing it to the given output stream,
     * whitespace characters will be ignored.
     *
     * @return the number of bytes produced.
     */
    public int base64_decode(
            String                data,
            OutputStream    out)
            throws IOException
    {
        byte[]    bytes;
        byte    b1, b2, b3, b4;
        int        length = 0;

        int        end = data.length();

        while (end > 0)
        {
            if (!ignore(data.charAt(end - 1)))
            {
                break;
            }

            end--;
        }

        int    i = 0;
        int   finish = end - 4;

        while (i < finish)
        {
            while ((i < finish) && ignore(data.charAt(i)))
            {
                i++;
            }

            b1 = decodingTable[data.charAt(i++)];

            while ((i < finish) && ignore(data.charAt(i)))
            {
                i++;
            }
            b2 = decodingTable[data.charAt(i++)];

            while ((i < finish) && ignore(data.charAt(i)))
            {
                i++;
            }
            b3 = decodingTable[data.charAt(i++)];

            while ((i < finish) && ignore(data.charAt(i)))
            {
                i++;
            }
            b4 = decodingTable[data.charAt(i++)];

            out.write((b1 << 2) | (b2 >> 4));
            out.write((b2 << 4) | (b3 >> 2));
            out.write((b3 << 6) | b4);

            length += 3;
        }

        if (data.charAt(end - 2) == padding)
        {
            b1 = decodingTable[data.charAt(end - 4)];
            b2 = decodingTable[data.charAt(end - 3)];

            out.write((b1 << 2) | (b2 >> 4));

            length += 1;
        }
        else if (data.charAt(end - 1) == padding)
        {
            b1 = decodingTable[data.charAt(end - 4)];
            b2 = decodingTable[data.charAt(end - 3)];
            b3 = decodingTable[data.charAt(end - 2)];

            out.write((b1 << 2) | (b2 >> 4));
            out.write((b2 << 4) | (b3 >> 2));

            length += 2;
        }
        else
        {
            b1 = decodingTable[data.charAt(end - 4)];
            b2 = decodingTable[data.charAt(end - 3)];
            b3 = decodingTable[data.charAt(end - 2)];
            b4 = decodingTable[data.charAt(end - 1)];

            out.write((b1 << 2) | (b2 >> 4));
            out.write((b2 << 4) | (b3 >> 2));
            out.write((b3 << 6) | b4);

            length += 3;
        }

        return length;
    }
}

/*
该对象在后台启动线程的控制下，从系统输入设备中读取字符并对该字符进行分析。
如果遇到行结束标志，则返回该线程获取的字符串对象password。
后台线程对象MaskingThread的作用:
该线程对象周期地刷新终端窗口，其目的在于屏蔽用户输入的字符，使该字符不能够在窗口中显示出来。
该线程对象的定义为：
*/

class MaskingThread extends Thread{
    private boolean stop = false;
    private int index;
    private String initial;

    public MaskingThread(String initial){
        this.initial = initial;
    }

    public void run(){
        while(!stop)
        {
            try
            {
                sleep(1);
            }
            catch (InterruptedException ex){
                ex.printStackTrace();
            }
            if (!stop){
                System.out.print("\r" + initial + " \r" + initial);
            }
            System.out.flush();
        }
    }

    public void stopMasking(){
        this.stop = true;
    }
} 