/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication37;




import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author kashif
 */
public class JavaApplication37 {
    static ArrayList<main_table> main_table_arraylist=new ArrayList<main_table>();
    static ArrayList<function_table> function_table=new ArrayList<function_table>(); 
    static ArrayList<store_all_class_table> store_all_class_table=new ArrayList<store_all_class_table>(); 
    static ArrayList<class_table> separate_class_table=new ArrayList<class_table>(); 
    String name_of_class = "";
    String type_of_class = "";
    String access_modifier_of_class = "";
    String catagory_of_class = "";
    String parent = "";
    
    static Stack<Integer> stack = new Stack<Integer>(); 
    int stack_number=0;
    String name_of_function_table = "";
    String type_of_funtion_table = "";
    static Stack<Integer> copy_of_stack = new Stack<Integer>();     
    
    String access_modifier_of_datable = "";
    String name_of_data_table = "";
    String type_of_data_table = "";
    String type_modifier_of_data_table = "";
    
    String lookup_variable_for_function_table_to_class_table="";
    
    boolean insert_of_main_table_is_true=true;
    
    
    //String lefttype, String righttype, String operator
//    String lefttype="";
//    String righttype="";
    String operator="";
//    String resultant_type="";
    String type="";
    
    static int index = 0 ;
    public static boolean t=true;
    public Scanner x;
    public static String inputWholeFile = "";
    public static ArrayList<Tokenization> arrayList=new ArrayList<Tokenization>();
    public static String[][] Keywords = {
        {"do","doClass"},
        {"boolean","datatype"},
        {"char","datatype"},
        {"float","datatype"},
        {"int","datatype"},
        {"void","voidClass"},
        {"while","whileClass"},
        {"for","foreachClass"},        // before  {"foreach","foreachClass"},
        {"co","breakContinueClass"},
        {"continue","breakContinueClass"},
        {"public","accessModifier"},
        {"private","accessModifier"},
        {"protected","accessModifier"},
        {"static","staticClass"},
        {"final","finalClass"},
        {"abstract","abstractClass"},
        {"new","newClass"},
        {"class","class"},
        {"interface","interfaceClass"},
        {"super","superClass"},
        {"this","thisClass"},
        {"extends","extendsClass"},
        {"implement","implementClass"},
        {"try","tryClass"},      //before {"try","tryFinalClass"},
        {"finally","finallyClass"},     //before {"finally","tryFinalClass"},
        {"catch","catchClass"},
        {"return","returnClass"},
        {"if","ifClass"},
        {"else","elseClass"},
        {"instanceof","instanceOfClass"},
        {"throws","throwsClass"},
        {"throw","throwClass"},
        {"native","nativeClass"},
        {"enum","enumClass"},
        
    };
    
    // Arrangment listOfOperator ki change nai kar na warna output change ho ji ga. Or dusri baat yah hai ka jo dwo wali operator hai ann ka pala operator single wale operator ma samal ho na zoori hai for e.g. (<= ka <) single wale operator(<) mai samal ho na zoori hai. varna output wrong ha gai ga.  
    public static String[][] listOfOperator = {
        {"%=","AssignmentOperator"},
        {"/=","AssignmentOperator"},
        {"*=","AssignmentOperator"},
        {"-=","AssignmentOperator"},
        {"+=","AssignmentOperator"},
        {"&&","LogicalAnd"},
        {"||","LogicalOR"},
        {"<=","RelationalOperator"},
        {">=","RelationalOperator"},
        {"==","RelationalOperator"},   // pehle yeh ta {"==","EqualtiyOperator"}
        {"!=","RelationalOperator"},    // pehle yeh ta {"==","EqualtiyOperator"}
        {"!","additive"},
        {"&","bitwiseAND"},
        {"|","bitwiseOR"},
        {"+","additive"},
        {"-","additive"},
        {"*","multiplicative"},
        {"/","multiplicative"},
        {"%","mode"},
        {"=","assignment"},
        {"<","RelationalOperator"},
        {">","RelationalOperator"},
        
        
    };
    
   /* public static String[] punc = {
        "{","[","]", "]", ";", "\'", "\"", ".", ",", "(", ")"
    };  */
    public static JavaApplication37 read;
    
    public static void main(String[] args) {
        
        char[] ch;
        read = new JavaApplication37();
        read.openFile();
        read.readFile();
        read.closeFile();
        System.out.println("Yours input : ");
        System.out.println(inputWholeFile);
        ch = new char[inputWholeFile.length()];
         for (int i = 0; i < inputWholeFile.length(); i++) {
            ch[i] = inputWholeFile.charAt(i);
        }
//        for (char c : ch) {
//            System.out.println(c);
//        } 
        for(int index =0; index<ch.length;index++){
            second:{
               
             if(ch[index]>=65 && ch[index]<=90 || (ch[index]>=97 && ch[index]<=122) || (ch[index]==36) || (ch[index]==95)){
//                 read.isID(ch,index);
//                 System.out.println(read.isID(ch,index));
                   List<Object> id = read.isID(ch,index);
//                   System.out.println(id.get(0));
                   index = (Integer)id.get(1);
                   int lineNumber = read.lineNumber(ch, index);
//                   System.out.println("hello");
//                   System.out.println(lineNumber);
                   List<Object> keywordVariable = read.isKW((String)id.get(2));
                   List<Object> boolconstant = read.booleanConstant((String)id.get(2));
                   if((Boolean)id.get(0)){
                       if((Boolean)keywordVariable.get(0)){
                           arrayList.add(new Tokenization((String)keywordVariable.get(2), (String)keywordVariable.get(1), lineNumber));
                           if(t) break second;
                       }
                       else if((Boolean)boolconstant.get(0)){
                           arrayList.add(new Tokenization((String)boolconstant.get(1), (String)boolconstant.get(2), lineNumber));
                           if(t) break second;
                       }
                       else{
                           arrayList.add(new Tokenization("Identifier", (String)id.get(2), lineNumber));
                           if(t) break second;
                       }
                       
                    }
                   else{
                     if(t) break second;  
                     //index=index;
                     
                 }
             }
             
             
             
             
             
            if((ch[index]>=48 && ch[index]<=57) || ch[index]==46){          // float
                 List<Object> floatConst = read.floatConstant(ch, index);
                 index=(Integer)floatConst.get(1);             
                 if((Boolean)floatConst.get(0)){
                     
                     int lineNumber = read.lineNumber(ch, index);       
                     arrayList.add(new Tokenization("floatConstant", (String)floatConst.get(2), lineNumber));
                     if(t) break second;
                 }
                 else{
                     
                     if(t) break second;
//                     index2=(Integer)floatConst.get(3);
                 }
                 
             }
            
            if( (ch[index]=='[' || ch[index]==']' || ch[index]=='{' || ch[index]=='}' || ch[index]==',' || ch[index]==';' || ch[index]=='(' || ch[index]==')')){
                 int lineNumber = read.lineNumber(ch, index);
                 arrayList.add(new Tokenization(String.valueOf(ch[index]), String.valueOf(ch[index]), lineNumber));
                 if(t) break second;
             }
             
         /*    if(ch[index]>=48 && ch[index]<=57){                                         // Integer
                 List<Object> intConst = read.integerConstant(ch, index);
                 index = (Integer)intConst.get(1);
//                 int lineNumber = read.lineNumber(ch, index);                  
                 if((Boolean)intConst.get(0)){ 
                     
                     int lineNumber = read.lineNumber(ch, index);       
                     arrayList.add(new Tokenization("integerConstant", (String)intConst.get(2), lineNumber));
                     if(t) break second;
                 }
                 else{
                        
                     if(t) break second;
                     
                     //arrayList.add(new Tokenization("Invalid", , lineNumber));
                 }
                 
             }   */
             
             if(ch[index]=='\''){                                                      //character
                 List<Object> charCons = read.charConstant(ch, index);
                    index = (Integer)charCons.get(1);
                 if((Boolean)charCons.get(0)){
                     int lineNumber = read.lineNumber(ch, index);
                     String charr = (String)charCons.get(2);
                     //charr = charr.substring(0,charr.length()-1);
                     charr = charr.substring(1,charr.length()-1);
                     arrayList.add(new Tokenization("charConstant", charr, lineNumber));
                     if(t) break second;
                 }
                 else{
                     index=index;
                     if(t) break second;
                     //index2=(Integer)charCons.get(3);
                 }
             }
             
             if(ch[index]=='/' && index<ch.length-1 && ch[index+1]=='/'){
                 List<Object> singleLineComm = read.singleLineComment(ch, index);    //comment
                   index = (Integer)singleLineComm.get(1);
                 if((Boolean)singleLineComm.get(0)){
                     int lineNumber = read.lineNumber(ch, index);      
                     arrayList.add(new Tokenization((String)singleLineComm.get(2), (String)singleLineComm.get(3), lineNumber));
                     if(t) break second;
                 }
                 else{
     
                     if(t) break second;
                     //index2=(Integer)singleLineComm.get(3);
                 }
             }
             
             if(ch[index]=='/' && index<ch.length-1 && ch[index+1]=='*'){                       //MultiplineComment
                 List<Object> multiplineCommment = read.multipleComment(ch, index);
                 index = (Integer)multiplineCommment.get(1);
                 if((Boolean)multiplineCommment.get(0)){
                     if(t) break second;
                 }
                 else{
                     if(t) break second;
                 }
             }
             
             
             if(ch[index]=='+' || ch[index]=='-' || ch[index]=='*' || ch[index]=='/' || ch[index]=='%' || ch[index]=='&' || ch[index]=='|' || ch[index]=='=' || ch[index]=='<' || ch[index]=='>' || ch[index]=='!'){
                 List<Object> operator = read.isOperator(ch, index);                
                 index = (Integer)operator.get(1);                                      //Operator
                 if((Boolean)operator.get(0)){
                     int lineNumber = read.lineNumber(ch, index);      
                     arrayList.add(new Tokenization((String)operator.get(3), (String)operator.get(2), lineNumber));
                     if(t) break second;
                 }
                 else{
                     if(t) break second;
                     //index2=(Integer)operator.get(3);
                 }
             }
             
             
             // puntuator . is not included because it is included in FloatConstant Function
             if( ch[index]=='[' || ch[index]==']' || ch[index]=='{' || ch[index]=='}' || ch[index]=='.' || ch[index]==',' || ch[index]==';' || ch[index]=='(' || ch[index]==')'){
                 int lineNumber = read.lineNumber(ch, index);
                 arrayList.add(new Tokenization(String.valueOf(ch[index]), String.valueOf(ch[index]), lineNumber));
                 if(t) break second;
             }
             
             
             
             if(ch[index]=='"'){                                                  //String
                 List<Object> stringCons = read.stringConstant(ch, index);
                 index = (Integer)stringCons.get(1);
                 if((Boolean)stringCons.get(0)){
                     int lineNumber = read.lineNumber(ch, index);
                     String charr = (String)stringCons.get(2);
                     charr = charr.substring(1,charr.length()-1);
                     arrayList.add(new Tokenization("StringConstant", charr, lineNumber));
                     if(t) break second;
                 }
                 else{
                     if(t) break second;
                     //index2=(Integer)stringCons.get(3);
                 }
             }
             
             if( !(ch[index]>=65 && ch[index]<=90 || (ch[index]>=97 && ch[index]<=122) || (ch[index]==36) || (ch[index]==95)) && !((ch[index]>=48 && ch[index]<=57) || ch[index]==46) && !( (ch[index]=='[' || ch[index]==']' || ch[index]=='{' || ch[index]=='}' || ch[index]==',' || ch[index]==';' || ch[index]=='(' || ch[index]==')')) && !(ch[index]=='\'')  && !(ch[index]=='+' || ch[index]=='-' || ch[index]=='*' || ch[index]=='/' || ch[index]=='%' || ch[index]=='&' || ch[index]=='|' || ch[index]=='=' || ch[index]=='<' || ch[index]=='>' || ch[index]=='!') && !( ch[index]=='[' || ch[index]==']' || ch[index]=='{' || ch[index]=='}' || ch[index]=='.' || ch[index]==',' || ch[index]==';' || ch[index]=='(' || ch[index]==')') && !(ch[index]=='"') && !(ch[index]=='\n') && !(ch[index]==' ')                 ){
                 List<Object> ann_sab_ka_allawa_character = read.those_character_which_does_not_start_from_languageSpecfication(ch, index);
                 index = (Integer)ann_sab_ka_allawa_character.get(1);
                 if((Boolean)ann_sab_ka_allawa_character.get(0)){
                     int lineNumber = read.lineNumber(ch, index);
                     String charr = (String)ann_sab_ka_allawa_character.get(2);
                     arrayList.add(new Tokenization("Invalid", charr, lineNumber));
                     if(t) break second;
                 }
                 
             }
             
             
             // we saved newline into token list because for the checking For SignedFloat number. But then we remove newline from token list. 
             if(ch[index]=='\n'){
                 int lineNumber = read.lineNumber(ch, index);
                 arrayList.add(new Tokenization("newline", "\n", lineNumber));
                 if(t) break second;
             }
             
          
             
        }//second     
        
        }  //for
        
        //Signed Float Number
        for (int i = 0; i < arrayList.size(); i++) {
            
            if(( i>=2 && arrayList.get(i).classPart=="floatConstant" && arrayList.get(i-1).classPart=="additive"  && arrayList.get(i-2).classPart!="floatConstant" && arrayList.get(i-2).classPart!="SignedFloat"    )            || (i==1 && arrayList.get(i).classPart=="floatConstant" && arrayList.get(i-1).classPart=="additive"    )        ) {
                String a = arrayList.get(i-1).valuePart+arrayList.get(i).valuePart;
                int b = arrayList.get(i).LineNummber;
                arrayList.remove(i-1);
                arrayList.set(i-1, new Tokenization("SignedFloat", a, b));
                i=i-1;
              
            }
            //System.out.println("("+arrayList.get(i).classPart+","+arrayList.get(i).valuePart+","+arrayList.get(i).LineNummber+")");
        }
        
        
        // removing newline form token list.
        int a=0;
        for (int i = 0; i < arrayList.size(); i++){
            if(arrayList.get(i).classPart.equals("newline") ){
                arrayList.remove(i);
                i=-1;
                
            }
        }
        
        //Getting Iterator (Output) 
        Iterator itr=arrayList.iterator();  
        //traversing elements of ArrayList object
        System.out.println("------------xxxxxxxxxxxxxxxx--------------");
        
//        while(itr.hasNext()){
//            Tokenization st=(Tokenization)itr.next();  
//            System.out.println("("+st.classPart+","+st.valuePart+","+st.LineNummber+")"); 
//        }
        
        
           try{                  
         System.out.println(read.s());
         
         System.out.println("```````````````````````````````````````````````````");
         
         for(int i=0;i<main_table_arraylist.size();i++){
             
             System.out.println(main_table_arraylist.get(i).name +"  "+main_table_arraylist.get(i).type+"    "+ main_table_arraylist.get(i).access_modifier+"    "+ main_table_arraylist.get(i).category+"      "+ main_table_arraylist.get(i).parent);
         }
         
         
         System.out.println("-----------------------------------------------------");
         
         for(int i=0;i<store_all_class_table.size();i++){
             for(int j=0;j<store_all_class_table.get(i).separate_class_table.size();j++){
                              System.out.println( store_all_class_table.get(i).separate_class_table.get(j).name+"  "+store_all_class_table.get(i).separate_class_table.get(j).type+"    "+ store_all_class_table.get(i).separate_class_table.get(j).access_modifier+"    "+ store_all_class_table.get(i).separate_class_table.get(j).type_modifier+"      "+ store_all_class_table.get(i).separate_class_table.get(j).link);

             }
         }
         
         System.out.println("######################################################");
         
         for(int i=0;i<function_table.size();i++){
             
             System.out.println(function_table.get(i).name +"  "+function_table.get(i).type+"    "+ function_table.get(i).scope);
         }
         
           }
           catch(Exception e){
             System.out.println(false);
         }
         
    }
    
   /* public List<Object> punctuation(char[] chh, int ii){
        return Arrays.asList(true,ii,chh[ii]);
    } */
    
    /*public List<Object> stringConstant(char[] ch, int ii){
        String s = "";
        int i=ii;
        if(i!=ch.length-1){
            for(i=ii; i<ch.length;i++){
            if(ch[i+1]=='"' && ch[i]!='\\'){
                s = s +ch[i];
                return Arrays.asList(true,i+1,s);
            }
            else if(ch[i]=='\\'){
                if(ch[i+1]=='n' || ch[i+1]=='t' || ch[i+1]=='r' || ch[i+1]=='\\' || ch[i+1]=='"'){
                    s = s + ch[i] + ch[i+1];
                    i=i+1;
                }
                else{
                    return Arrays.asList(false,i+1,s);
                }
            }
            else{
                s = s+ch[i];
            }
        }
        return Arrays.asList(false,i-1,s);
        }
        
        else{
            return Arrays.asList(false,i,s);
        }
        
    }*/
    
    public List<Object> those_character_which_does_not_start_from_languageSpecfication(char[] ch, int i){
        int index;
        String s="";
        for(index=i;index<ch.length;index++){
             if( !(ch[index]>=65 && ch[index]<=90 || (ch[index]>=97 && ch[index]<=122) || (ch[index]==36) || (ch[index]==95)) && !((ch[index]>=48 && ch[index]<=57) || ch[index]==46) && !( (ch[index]=='[' || ch[index]==']' || ch[index]=='{' || ch[index]=='}' || ch[index]==',' || ch[index]==';' || ch[index]=='(' || ch[index]==')')) && !(ch[index]=='\'')  && !(ch[index]=='+' || ch[index]=='-' || ch[index]=='*' || ch[index]=='/' || ch[index]=='%' || ch[index]=='&' || ch[index]=='|' || ch[index]=='=' || ch[index]=='<' || ch[index]=='>' || ch[index]=='!') && !( ch[index]=='[' || ch[index]==']' || ch[index]=='{' || ch[index]=='}' || ch[index]=='.' || ch[index]==',' || ch[index]==';' || ch[index]=='(' || ch[index]==')') && !(ch[index]=='"') && !(ch[index]=='\n') && !(ch[index]==' ')                 ){
                 s = s + ch[index];
             }
             else{
                 return Arrays.asList(true,index-1,s);
             }
            
        }
        return Arrays.asList(true,index,s);
        
    }
    
    
    public List<Object> multipleComment(char[] ch, int ii){
        int i;
        for(i=ii; i<ch.length;i++){
            if(ch[i]=='*' && i<ch.length-1 && ch[i+1]=='/'   ){
                return Arrays.asList(true,i+1);
            }
        }
        return Arrays.asList(false,i);
    }
    
    
    public List<Object> stringConstant(char[] ch, int ii){
        String s = "";
        int i;
        int count = 0 ;
            for(i=ii; i<ch.length;i++){
            if(ch[i]=='"' && count>=1){
                s = s +ch[i];
                return Arrays.asList(true,i,s);
            }
            else if(ch[i]=='\\'){
                if( i<ch.length-1 &&(ch[i+1]=='n' || ch[i+1]=='t' || ch[i+1]=='r' || ch[i+1]=='\\' || ch[i+1]=='"')){
                    s = s + ch[i] + ch[i+1];
                    count = count+1;
                    i=i+1;
                }
                else if(i<ch.length-1){
                    s = s + ch[i]+ ch[i+1];
                    int lineNumber = read.lineNumber(ch, i);
                    arrayList.add(new Tokenization("Invalid", s, lineNumber));
                    return Arrays.asList(false,i+1,s);
                }
                else{
                    s = s + ch[i];
                    int lineNumber = read.lineNumber(ch, i);
                    arrayList.add(new Tokenization("Invalid", s, lineNumber));
                    return Arrays.asList(false,i+1,s);
                }
            }
            else if(ch[i]=='\n'){
                int lineNumber = read.lineNumber(ch, i);
                arrayList.add(new Tokenization("Invalid", s, lineNumber));       
                return Arrays.asList(false,i-1,s);
            }
            else{
                s = s+ch[i];
                count=count+1;
            }
        }
        int lineNumber = read.lineNumber(ch, i);
        arrayList.add(new Tokenization("Invalid", s, lineNumber));       
        return Arrays.asList(false,i-1,s);
        
        
        
        
    }
    
    public List<Object> singleLineComment(char[] ch, int ii){
        
        String c="";
        int count =0;
        int i;
        for(i=ii;i<ch.length;i++){
            if(ch[i]=='/' && count<=1){
                count = count+1;
                c=c+String.valueOf(ch[i]);
            }
            else if(c.equals("/")){
                return Arrays.asList(true, i-1,"multiplicative","/");
            }
            else if(c.equals("//")){
                if(ch[i]=='\n'){
                    return Arrays.asList(false,i-1);
                }
            }
            else{
                return Arrays.asList(false,i-1);
            }
        }
        //int lineNumber = read.lineNumber(ch, i);
        //arrayList.add(new Tokenization("Invalid", identifier, lineNumber));
        return Arrays.asList(false,i-1);
    }
    
    public List<Object> charConstant(char[] chh, int ii){
        String a="";
        String b="";
        String[] characters = {"'1'","'2'", "'3'", "'4'", "'5'", "'5'", "'6'", "'7'", "'8'", "'9'", "'0'", "'!'", "'@'", "'#'", "'$'", "'%'", "'^'", "'&'", "'*'", "'('", "')'", "'-'", "'_'", "'='", "'+'", "'['", "']'", "'|'", "';'", "':'", "'\''", "'{'", "'}'", "','", "'<'", "'.'", "'>'", "'/'", "'?'", "'q'", "'w'", "'e'", "'r'", "'t'", "'y'", "'u'", "'i'", "'o'", "'p'", "'a'", "'s'", "'d'", "'f'", "'g'", "'h'", "'j'", "'k'", "'l'", "'z'", "'x'", "'c'", "'v'", "'b'", "'n'", "'m'", "'Q'", "'W'", "'E'", "'R'", "'T'", "'Y'", "'U'", "''", "'I'", "'O'", "'P'", "'A'", "'S'", "'D'", "'F'", "'G'", "'H'", "'J'", "'K'", "'L'", "'Z'", "'X'", "'C'", "'V'", "'B'", "'N'", "'M'"};
        String[] specialCharacter ={"'\\n'", "'\\r'","'\\t'"};
        int i,y;
        for(i=ii; i<chh.length;i++){
            if(i==ii+3){
                break;
            }
            else{
                a = a + String.valueOf(chh[i]);
            }
        }
        for(y=ii; y<chh.length && chh[y]!='\n';y++){
            if(y==ii+4){
                break;
            }
            else{
                b = b + String.valueOf(chh[y]);
            }
        }
        for(int z =0 ; z<specialCharacter.length;z++){
                if(specialCharacter[z].equals(b)){
                    return Arrays.asList(true,y-1, specialCharacter[z]);
                }   
        }
        for(int z =0 ; z<characters.length;z++){
                if(characters[z].equals(a)){
                    return Arrays.asList(true,i-1, characters[z]);
                }   
        }
        int lineNumber = read.lineNumber(chh, i);
        arrayList.add(new Tokenization("Invalid", b, lineNumber));
        return Arrays.asList(false,y-1);
        
    }
    
    
    public List<Object> booleanConstant(String boolConstant){
        String[] boolTF = {"true", "false"};
        for (int i = 0; i < boolTF.length; i++) {
            
            if(boolConstant.equals(boolTF[i])){
                 return Arrays.asList(true, "boolConstant", boolTF[i]);
            }
        }
        
        return Arrays.asList(false);
        
    }
    
    public void openFile(){
        try{
            x= new Scanner(new File("input.txt"));
        }
        catch(Exception e){
            System.out.println("could not find file");
        }
    
    }
    
    public int lineNumber(char[] chh, int index){
        String str = String.valueOf(chh);
        int Lnumber=1;
        for(int i = 0; i<chh.length;i++){
            //if(i==8)System.out.println(str.indexOf(chh[i]));
            if(chh[i]=='\n' && index<=str.indexOf(chh[i],i)){
                
                return Lnumber;
            }
            else if(chh[i]=='\n'){
                Lnumber = Lnumber+1;
            }
        }
        return Lnumber;
    }
    
    
    
    
    public void readFile(){
        int i = 0;
        while(x.hasNext()){
            String a = x.nextLine();
            if(i==0){
                inputWholeFile = inputWholeFile+""+a;
                i++;
            }
            else{
                inputWholeFile = inputWholeFile+"\n"+a;
            }
        }
    }
    
    public void closeFile(){
        x.close();
    }
    
/*    public List<Object> isID(char[] ch, int index) {
        int count = 0;
        int i;
        String identifier = "";
        for(i = index ; i<ch.length;i++){
            
            if( (ch[i]>=48 && ch[i]<=57) || (ch[i]>=65 && ch[i]<=90) || (ch[i]>=97 && ch[i]<=122) || (ch[i]==36) || (ch[i]==95)){     
                    
                  identifier = identifier + String.valueOf(ch[i]);
                  count= count +1;
            }        
            else{
                i=i-1;
                return Arrays.asList(true,i , identifier);
            }
            
        }
        
       return Arrays.asList(true,i , identifier); 
        
    }   */
    
   /* public List<Object> integerConstant(char[] ch, int ii){    // dekna kai 20g

        
        int i;
        String integerConstant = "";
        for(i = ii ; i<ch.length;i++){
            if(ch[i]>=48 && ch[i]<=57){     
                    
                  integerConstant = integerConstant + String.valueOf(ch[i]);
                  
            }        
            else{
                i=i-1;
                return Arrays.asList(true,i , integerConstant);
            }
            
        }   
        
        return Arrays.asList(true,i , integerConstant); 
    }  */
    
    public List<Object> floatConstant(char[] ch, int index){    // dekna kai 20g
        // hum yehi bi kar sakte hai ka ghar dotCount ki value 0 hai tu INTEGERCONSTANT de or ghar dotCount ki value 1 hai tu FlOATCONSTANT DE output me.
        
        String s = "";
        int dotCount = 0;
        int alphbatCount = 0;
        int digitCount = 0;
        int i;
        for(i = index; i<ch.length; i++){
            if(ch[i]>=48 && ch[i]<=57){
                s = s + ch[i];
                digitCount = digitCount +1;
            }
            if(ch[i]=='.' && dotCount==0){
                s = s + ch[i];
            }
            if(ch[i]=='.' && i<ch.length-1 &&  !(ch[i+1]>=48 && ch[i+1]<=57) && dotCount==0 ){
                int lineNumber = read.lineNumber(ch, i);
                if(s.length()>=2){
                    arrayList.add(new Tokenization("floatConstant", s.substring(0, s.length() - 1), lineNumber));
                }
                arrayList.add(new Tokenization(".", ".", lineNumber));
                return Arrays.asList(false , i, s); 
                
            }
            if(ch[i]=='.'){
                dotCount  = dotCount+1;
            }
            if(  !('\n'==ch[i] || ch[i]==' ' || ch[i]==',' || ch[i]==';' || ch[i]=='!'|| ch[i]=='&' || ch[i]=='=' || ch[i]=='+' || ch[i]=='-' || ch[i]=='*' || ch[i]=='/' || ch[i]=='%'  || ch[i]=='|' || ch[i]=='<' || ch[i]=='>' || ch[i]=='(' || ch[i]==')' || ch[i]=='{' || ch[i]=='}' || ch[i]=='\'' ||  ch[i]=='"' )  && !(ch[i]>=48 && ch[i]<=57) && !(ch[i]=='.')                             ){
                s = s+ch[i];
                alphbatCount =alphbatCount+1;
            }
            if( ('\n'==ch[i] || ch[i]==' ' || ch[i]==',' || ch[i]==';' || ch[i]=='!'|| ch[i]=='&' || ch[i]=='=' || ch[i]=='+' || ch[i]=='-' || ch[i]=='*' || ch[i]=='/' || ch[i]=='%'  || ch[i]=='|' || ch[i]=='<' || ch[i]=='>' || ch[i]=='(' || ch[i]==')' || ch[i]=='{' || ch[i]=='}' || ch[i]=='\'' ||  ch[i]=='"' ) && (dotCount==0 || dotCount==1)    ){
                if(digitCount==0){
                    int lineNumber = read.lineNumber(ch, i);
                    arrayList.add(new Tokenization(".", s, lineNumber));
                    return Arrays.asList(false , i-1, s);
                }
                if((alphbatCount>0)  ){
                    int lineNumber = read.lineNumber(ch, i);
                    arrayList.add(new Tokenization("Invalid", s, lineNumber));
                    return Arrays.asList(false , i-1, s);
                }
                return Arrays.asList(true , i-1, s);
            }
            if(ch[i]=='.' && dotCount==2 && alphbatCount==0){
                if(digitCount==0){
                    int lineNumber = read.lineNumber(ch, i);
                    arrayList.add(new Tokenization(".", ".", lineNumber));
                    return Arrays.asList(false , i-1, s);
                }
                return Arrays.asList(true , i-1, s); 
            }
            if(ch[i]=='.' && dotCount==2 && alphbatCount!=0){
                int lineNumber = read.lineNumber(ch, i);
                arrayList.add(new Tokenization("Invalid", s, lineNumber));
                return Arrays.asList(false , i-1, s); 
            }
            if(i==ch.length-1&& (dotCount==0 || dotCount==1) && alphbatCount==0 && digitCount>0){
                return Arrays.asList(true , i, s); 
            }
            
        }
        
        if(digitCount==0){
                    int lineNumber = read.lineNumber(ch, i);
                    arrayList.add(new Tokenization(".", ".", lineNumber));
                    return Arrays.asList(false , i-1, s);
                    }
        int lineNumber = read.lineNumber(ch, i);
        arrayList.add(new Tokenization("Invalid", s, lineNumber));
        return Arrays.asList(false , i, s); 
    }
    
    
  /*  public List<Object> integerConstant(char[] ch, int index){    // dekna kai 20g

        int count = 0;
        int i;
        String integerConstant = "";
        for(i = index ; i<ch.length;i++){
            if( '\n'==ch[i] || ch[i]==' ' || ch[i]==',' || ch[i]==';' || ch[i]=='!' || ch[i]=='=' || ch[i]=='+' || ch[i]=='-' || ch[i]=='*' || ch[i]=='/' || ch[i]=='%'  || ch[i]=='|' || ch[i]=='<' || ch[i]=='>' || ch[i]=='(' || ch[i]==')' || ch[i]=='{' || ch[i]=='}' || ch[i]=='\'' || ch[i]=='.' || ch[i]=='"'  ){     
               if(count==0){
                   return Arrays.asList(true , i-1, integerConstant); 
               }
               else{
                   int lineNumber = read.lineNumber(ch, i);
                   arrayList.add(new Tokenization("Invalid", integerConstant, lineNumber));
                   return Arrays.asList(false , i-1, integerConstant); 
               }
                  
                  
            }
            
            else if (i==ch.length-1 &&  ( ch[i]>=48 && ch[i]<=57)    )  {
                integerConstant = integerConstant + String.valueOf(ch[i]);
                if(count==0){
                   return Arrays.asList(true , i, integerConstant); 
               }
               else{
                   //integerConstant = integerConstant + String.valueOf(ch[i]);
                   int lineNumber = read.lineNumber(ch, i);
                   arrayList.add(new Tokenization("Invalid", integerConstant, lineNumber));
                   return Arrays.asList(false , i, integerConstant); 
               }   
            }
            
            else if( (ch[i]>=48 && ch[i]<=57)){     
                    
                  integerConstant = integerConstant + String.valueOf(ch[i]);
                  
            }
            else{
                count = count +1;
                integerConstant = integerConstant + String.valueOf(ch[i]);
            }   
            
        }
        
        int lineNumber = read.lineNumber(ch, i);
        arrayList.add(new Tokenization("Invalid", integerConstant, lineNumber));
        return Arrays.asList(false,i, integerConstant); 
        
    }  */
    
    public List<Object> isID(char[] ch, int index) {
        int i;
        int count = 0;
        String identifier = "";
        for(i = index ; i<ch.length;i++){
            if( '\n'==ch[i] || ch[i]==' ' || ch[i]==',' || ch[i]==';' || ch[i]=='!' || ch[i]=='=' || ch[i]=='+' || ch[i]=='-' || ch[i]=='*' || ch[i]=='/' || ch[i]=='%' || ch[i]=='&' || ch[i]=='|' || ch[i]=='<' || ch[i]=='>' || ch[i]=='(' || ch[i]==')' || ch[i]=='{' || ch[i]=='}' || ch[i]=='\'' || ch[i]=='.' || ch[i]=='"'  ){     
               if(count==0){
                   return Arrays.asList(true , i-1, identifier); 
               }
               else{
                   int lineNumber = read.lineNumber(ch, i);
                   arrayList.add(new Tokenization("Invalid", identifier, lineNumber));
                   return Arrays.asList(false , i-1, identifier); 
               }
                  
                  
            }
            
            else if (i==ch.length-1 &&  ( (ch[i]>=48 && ch[i]<=57) || (ch[i]>=65 && ch[i]<=90) || (ch[i]>=97 && ch[i]<=122) || (ch[i]==36) || (ch[i]==95))   )  {
                identifier = identifier + String.valueOf(ch[i]);
                if(count==0){
                   return Arrays.asList(true , i, identifier); 
               }
               else{
                    int lineNumber = read.lineNumber(ch, i);
                   arrayList.add(new Tokenization("Invalid", identifier, lineNumber));
                   return Arrays.asList(false , i, identifier); 
               }   
            }
            
            else if( (ch[i]>=48 && ch[i]<=57) || (ch[i]>=65 && ch[i]<=90) || (ch[i]>=97 && ch[i]<=122) || (ch[i]==36) || (ch[i]==95)){     
                    
                  identifier = identifier + String.valueOf(ch[i]);
                  
            }
            else{
                count = count +1;
                identifier = identifier + String.valueOf(ch[i]);
            }
            
            
        }
       int lineNumber = read.lineNumber(ch, i);
       arrayList.add(new Tokenization("Invalid", identifier, lineNumber)); 
       return Arrays.asList(false,i , identifier); 
        
    }
    
    
    
    
    
    public List<Object> isKW(String identifier){
//        int [][] sizeOfKW = new int[32][2];
        for(int i =0 ; i<Keywords.length;i++){
            for(int j =0; j<Keywords[i].length;j++){
                if(Keywords[i][j].equals(identifier)){
                    return Arrays.asList(true, Keywords[i][j], Keywords[i][j+1]);
                }   
            }
        }
        return Arrays.asList(false);
    }
    
    public List<Object> isOperator(char[] chh, int ii){
        String operator1= "";
        String operator2= "";
        String operator3= "";
        if(ii==chh.length-1){
            operator1 = String.valueOf(chh[ii]);
        }
        else{
            operator2= String.valueOf(chh[ii]);
            operator3= String.valueOf(chh[ii]) + String.valueOf(chh[ii+1]);
        }
        for(int i =0 ; i<listOfOperator.length;i++){
            for(int j =0; j<listOfOperator[i].length;j++){
                if(listOfOperator[i][j].equals(operator3)){
                    return Arrays.asList(true,ii+1, listOfOperator[i][j], listOfOperator[i][j+1] );
                }
                else if(listOfOperator[i][j].equals(operator2)){
                    return Arrays.asList(true,ii, listOfOperator[i][j], listOfOperator[i][j+1]);
                }
                else if(listOfOperator[i][j].equals(operator1)){
                    return Arrays.asList(true,ii, listOfOperator[i][j], listOfOperator[i][j+1]);
                }
            }
        }
          int lineNumber = read.lineNumber(chh, ii);
          arrayList.add(new Tokenization("Invalid", operator1+operator3, lineNumber));
          return Arrays.asList(false, ii+1);
          
    }
    
    
    
    
    public boolean s(){
        
        if( index<arrayList.size() && accessmodifier() ){
                if(za()){
                    
                    return true;
                    
                }
            }
      
        if(index==arrayList.size()) return true;
        
        
        return false;
        
    }
    
    public boolean za(){
        if(l()){
            
            if(q()){
                
                ;
                return true;
            }
        }
        else if(ldash()){
            
            if(qdash()){
                
                
                return true;
                
            }
        }
        else if (ldashdash()){
            
            if(qdashdash()){
                
                return true;
            
            }
        }
        else if (ldashdash()){
            
            if(qdashdash()){
                
                return true;
            
            }
        }
        else if (ldashdashdash()){
            
            if(qdashdashdash()){
                
                return true;
            
            }
        }
        
        return false;
        
    }
    
    public boolean l(){
        if(define()){
            
            
            if(arrayList.get(index).classPart.equals("{")){
                create_scope();
                index++;
                if(classbody()){
                    
                    return true;
                }
            }
        }
        
        return false;
        
    }
    
    public boolean ldash(){
        if(define2()){
            
            
            if(arrayList.get(index).classPart.equals("{")){
                index++;
                if(classbody2()){
                    
                    return true;
                }
            }
        }
        
        return false;
        
    }
    
    public boolean ldashdash(){
        if(define3()){
            
            
            if(arrayList.get(index).classPart.equals("{")){
                index++;
                if(classbody3()){
                    
                    return true;
                }
            }
        }
        
        return false;
        
    }
    
    public boolean q(){
        if(main()){
            
            if(classbody()){
                destory_scope();
                if(arrayList.get(index).classPart.equals("}")){
                    
                    //store_all_class_table.add(new store_all_class_table(separate_class_table));
                     ArrayList<class_table> arrayListClone =  (ArrayList<class_table>) separate_class_table.clone();
                    store_all_class_table.add(new store_all_class_table(arrayListClone));
                    separate_class_table.clear();
                    
                    name_of_class = "";
                    type_of_class = "";
                    access_modifier_of_class = "";
                    catagory_of_class = "";
                    parent = "";
                    index++;
                    if(s1()){
                        
                        return true;
                    }
                }
            }
        }
        else if(arrayList.get(index).classPart.equals("}")){
            destory_scope();
             ArrayList<class_table> arrayListClone =  (ArrayList<class_table>) separate_class_table.clone();
                    store_all_class_table.add(new store_all_class_table(arrayListClone));
                    separate_class_table.clear();
            
                    name_of_class = "";
                    type_of_class = "";
                    access_modifier_of_class = "";
                    catagory_of_class = "";
                    parent = "";
                    index++;
            
         
            if(s()){
                
                return true;
            }
        }
        return false;
    }
    
    public boolean qdash(){
        if(main()){
           
            if(classbody2()){
                
                if(arrayList.get(index).classPart.equals("}")){
                    index++;
                    if(s1()){
                        
                        return true;
                    }
                }
            }
        }
        else if(arrayList.get(index).classPart.equals("}")){
            name_of_class = "";
            type_of_class = "";
            access_modifier_of_class = "";
            catagory_of_class = "";
            parent = "";
            index++;
            if(s()){
                
                return true;
            }
        }
        return false;
    }
    
    public boolean qdashdash(){
        if(main()){
            
            if(classbody3()){
                
                if(arrayList.get(index).classPart.equals("}")){
                    store_all_class_table.add(new store_all_class_table(separate_class_table));
                    separate_class_table.clear();
                    name_of_class = "";
                    type_of_class = "";
                    access_modifier_of_class = "";
                    catagory_of_class = "";
                    parent = "";
                    index++;
                    if(s1()){
                        
                        return true;
                    }
                }
            }
        }
        else if(arrayList.get(index).classPart.equals("}")){
            store_all_class_table.add(new store_all_class_table(separate_class_table));
                    separate_class_table.clear();
                    name_of_class = "";
                    type_of_class = "";
                    access_modifier_of_class = "";
                    catagory_of_class = "";
                    parent = "";
                    index++;
            if(s()){
                
                return true;
            }
        }
        return false;
    }
    
    
    public boolean ldashdashdash(){
        if(arrayList.get(index).classPart.equals("enumClass")){
            index++;
            if(arrayList.get(index).classPart.equals("Identifier")){
                index++;
                if(enuminher()){
                    if(arrayList.get(index).classPart.equals("{")){
                        index++;
                        if(enumbody()){
                            return true;
        }
            
        }
        }
        }
            
        }
        return false;
    }
    
    
    
    
    
    
    
    public boolean qdashdashdash(){
        if(main()){
            
            if(enumbody()){
                
                if(arrayList.get(index).classPart.equals("}")){
                    index++;
                    if(s1()){
                        
                        return true;
                    }
                }
            }
        }
        else if(arrayList.get(index).classPart.equals("}")){
            index++;
            if(s()){
                
                return true;
            }
        }
        return false;
    }
    
    
    
//   String name_of_class = "";
//    String type_of_class = "";
//    String access_modifier_of_class = "";
//    String catagory_of_class = "";
//    String parent = ""; 
    
   
    public boolean accessmodifier(){
        //String a1[] = {"}","accessModifier","finalClass","staticClass","Identifier","datatype"};
        String a1[] = {"accessModifier","finalClass","class","abstractClass","interfaceClass","enumClass","Identifier","datatype"};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(arrayList.get(index).classPart.equals("accessModifier")){
                    access_modifier_of_class=arrayList.get(index).valuePart;
                    
                    access_modifier_of_datable=arrayList.get(index).valuePart;
                    index++;
                    return true;
                    }
                    else{  return true; }
                }
        }
        
        
        
        return false;
    }
    
    
    public boolean s1(){
        //String a1[] = {"}","accessModifier","finalClass","staticClass","Identifier","datatype"};
        String a1[] = {"accessModifier","finalClass","class","abstractClass","interfaceClass","enumClass","$"};
        for(int i=0; i<a1.length;i++){
            if(arrayList.get(index).classPart.equals(a1[i])){
                if(accessmodifier()){
            
                    if(mot()){
                
                    return true;
                    }
                }
                else{  return true;     }
                
            }
            
        }
          
        return false;
        
        
    }
    
    public boolean mot(){
        
        if(define()){
            
            if(arrayList.get(index).classPart.equals("{")){
                index++;
                if(classbody()){
                    
                    if(arrayList.get(index).classPart.equals("}")){
                        index++;
                        if(s1()){
                            
                            return true;
                        }
                    }
                }
            }
        }
        
        else if(define2()){
            
            if(arrayList.get(index).classPart.equals("{")){
                index++;
                if(classbody2()){
                    
                    if(arrayList.get(index).classPart.equals("}")){
                        index++;
                        if(s1()){
                            
                            return true;
                        }
                    }
                }
            }
        }
        
        else if(define3()){
            
            if(arrayList.get(index).classPart.equals("{")){
                index++;
                if(classbody3()){
                    
                    if(arrayList.get(index).classPart.equals("}")){
                        index++;
                        if(s1()){
                            
                            return true;
                        }
                    }
                }
            }
        }
        
        else if(arrayList.get(index).classPart.equals("enumClass")){       
            index++;
            if(arrayList.get(index).classPart.equals("Identifier")){
                index++;
                if(enuminher()){
                    
                    if(arrayList.get(index).classPart.equals("{")){
                        index++;
                        if(enumbody()){
                            
                            if(arrayList.get(index).classPart.equals("}")){
                                index++;
                                if(s1()){
                                    
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        
        return false;
        
    }
    

    public boolean define(){
        if(define1()){
           
            return true;
            }
            
            
            return false;
            
        }
    

    public boolean define1(){
        if(final1()){
            
            if(arrayList.get(index).classPart.equals("class")){
                type_of_class=arrayList.get(index).valuePart;             //insert_main_table
            index++;
            if(arrayList.get(index).classPart.equals("Identifier")){  
                name_of_class=arrayList.get(index).valuePart;
                
                index++;
                if(inheritance()){
                    
                    return true;
                }
            }
        }
        }
        
        else if (arrayList.get(index).classPart.equals("class")){
            type_of_class=arrayList.get(index).valuePart;
            index++;
            if(arrayList.get(index).classPart.equals("Identifier")){
                name_of_class=arrayList.get(index).valuePart;
                index++;
                if(inheritance()){
                    
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean main(){
        if(arrayList.get(index).valuePart.equals("public")){     
            index++;
            if(arrayList.get(index).valuePart.equals("static")){
            index++;
            if(arrayList.get(index).valuePart.equals("void"))
                index++;
            if(arrayList.get(index).valuePart.equals("main")){
                index++;
                if(arrayList.get(index).valuePart.equals("(")){
                    index++;
                    if(arrayList.get(index).valuePart.equals("String"))
                        index++;
                    if(arrayList.get(index).valuePart.equals("args"))
                        index++;
                    if(arrayList.get(index).valuePart.equals("[")){
                        index++;
                        if(arrayList.get(index).valuePart.equals("]"))
                            index++;
                        if(body()){
                            
                            return true;
                        }
                    }
                }
            }
        }
        }
        return false;
    }
    
    public boolean im(){
        
        String a1[] = {"public","}"};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).valuePart.equals(a1[i])){
                    
                    if(arrayList.get(index).valuePart.equals("public")){
                    index++;
                        if(new1()){
                
                        return true;
                        }
                    }
                    
                    else{ return true;}
                    
                    
                }
        }
        
        
        
        
        
        
        return false;
    }
    
    boolean new1(){
    
    if(arrayList.get(index).classPart.equals("staticClass")){
        index++;
        if(arrayList.get(index).classPart.equals("finalClass")){
            index++;
            if(assignst()){
                
                if(im()){
                    
                    return true;
                }
            }
            
        }
    }
    
    if(arrayList.get(index).classPart.equals("abstractClass")){
        index++;
        if(rettype()){
            
            if(arrayList.get(index).classPart.equals("Identifier")){
                index++;
                if(arrayList.get(index).classPart.equals("(")){
                    index++;
                    if(arrayList.get(index).classPart.equals(")")){
                        index++;
                        if(arrayList.get(index).classPart.equals(";")){
                            index++;
                            if(im()){
                               
                                return true;
                            }
                        }
                }
            }
            
        }
    }
        
    }
    return false;
    }
    
    
    public boolean define2(){
        if(define4()){
            
            return true;
        }
        return false;
    }
    
    public boolean define4(){
        if(abstract1()){
        
        if(arrayList.get(index).classPart.equals("class")){
            index++;
            if(arrayList.get(index).classPart.equals("Identifier")){
                index++;
                if(inheritance()){}{
                
                return true;
            }
            }
        }
    }
        return false;
    }
    
    public boolean classbody2(){
        //String a1[] = {"}","accessModifier","finalClass","staticClass","Identifier","datatype"};
        String a1[] = {"accessModifier","finalClass","abstractClass","staticClass","Identifier","datatype","}"};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(accessmodifier()){
            
                        if(care()){
                
                             return true;
                        }
                    }
                    
                    else {return false;}
                }
        }
        
        
        
        
       
        return false;
    }
    
    public boolean care(){
        if(zz()){
            
            if(classbody2()){
                
                return true;
            }
        }
        if(fam()){
            
            if(classbody2()){
                
                return true;
            }
        }
        return false;
    }
    
    public boolean classbody(){
        
        String a1[] = {"}","accessModifier","finalClass","staticClass","Identifier","datatype"};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(accessmodifier()){
            
                        if(zz()){
                
                        return true;
                        }
                    }
                    else{
                        return true;
                    }
                }
        }
        
        
        
        
        
        
         return false;
        
        
        
        
        
    }
    
    public boolean zz(){
        if(arrayList.get(index).classPart.equals("finalClass")){                
            index++;
            if(h()){
                
                return true;
            }
        }
        if(arrayList.get(index).classPart.equals("staticClass")){                      
            index++;
            if(final2()){
              
                if(h()){
                    
                    return true;
                }
            }
        }
        if(dt()){
            
            if(z()){
                
                return true;
            }
        }
        if(arrayList.get(index).classPart.equals("Identifier")){
            type_of_data_table  = arrayList.get(index).valuePart;
            name_of_data_table = arrayList.get(index).valuePart;
            index++;
            if(x()){
               
                return true;        
            }
        }
        return false;
    }
    
    public boolean x(){
        if(constructor()){
            
            if(classbody()){
                
                return true;
            }
        }
        if(o()){
            
            return true;
                   
        }
        return false;
    }
    
    public boolean h(){
        if(dt()){
           
            if(z()){
                
                return true;
            }
        }
        if(arrayList.get(index).classPart.equals("Identifier")){
            //type_modifier_of_data_table = arrayList.get(index).valuePart;
            index++;
            if(o()){
               
                return true;
            }
        }
        return false;
    }
    
    public boolean z(){
        if(arrayList.get(index).classPart.equals("Identifier")){
            name_of_data_table = arrayList.get(index).valuePart;
            index++;
            if(pp()){
                
                return true;
            }
        }
        return false;
    }
    
    
    
    
    public boolean pp(){
        if(function()){
           
            if(classbody()){
                
                return true;
            }
        }
        if(declare()){
            
            if(classbody()){
               
                return true;
            }
        }
        if(array1()){
          
            if(classbody()){
               
                return true;
            }
        }
        return false;
    }
    
    public boolean o(){
        if(arrayList.get(index).classPart.equals("Identifier")){
            name_of_data_table = arrayList.get(index).valuePart; 
            index++;
            if(a()){
                
                return true;
            }
        }
        return false;
    }
    
    public boolean a(){
        if(objdef()){
          
            if(classbody()){
               
                return true;
            }
        }
        if(function()){
           
            if(classbody()){
                
                return true;
            }
        }
        if(array()){
            
            if(classbody()){
                
                return true;
            }
        }
        return false;
    }
    
    public boolean define3(){
        if(define5()){
           
            return true;
        }
        return false;
    }
    
    public boolean define5(){
        if(arrayList.get(index).classPart.equals("interfaceClass")){
        type_of_class =arrayList.get(index).valuePart;            
            index++;
            if(arrayList.get(index).classPart.equals("Identifier")){
                name_of_class =arrayList.get(index).valuePart;  
                index++;
                if(kfood()){
                    
                    return true;
                }
            }
        }
        return false;
    }
    
    boolean kfood(){
        String a1[] = {"{","extendsClass"};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(arrayList.get(index).classPart.equals("extendsClass")){
            index++;
            if(multipleclassname()){
               
                return true;
            }
        }
                    else { 
                        insert_of_main_table_is_true=insert_main_table(name_of_class,type_of_class ,access_modifier_of_class ,catagory_of_class ,parent);
                        return true;}
                }
        }

        
        
        return false;
    } 
    
    public boolean classbody3(){
        String a1[] = {"}","public"};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).valuePart.equals(a1[i])){
                    if(im()){
            
            return true;
        }
                    else{return true;}
                }
        }
        
        
        
        
        
        return false;
    }
    
    public boolean oe(){
        if(ae()){
            
            if(oedash()){
             
                return true;
            }
        }
        return false;
    }
    
    public boolean oedash(){
        
        String a1[] = {")","||",";",","};                  
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).valuePart.equals(a1[i])){
                    if(arrayList.get(index).classPart.equals("LogicalOR")){                         
                    index++;
                        if(ae()){
               
                            if(oedash()){
                  
                                return true;
                            }
                        }
                        
                    }
                    else{return true;}
                }
        }
        
        
        
        
        
        
        
        return false;
    }
    
    public boolean ae(){
        if(re()){
            
            if(aedash()){
               
                return true;
            }
        }
        return false;
    }
    
    public boolean aedash(){
        
        String a1[] = {"||",")","&&",";",","};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).valuePart.equals(a1[i])){
                    if(arrayList.get(index).classPart.equals("LogicalAnd")){          
                        index++;
                            if(re()){
                
                                if(aedash()){
                    
                                    return true;
                                }
                            }
                            
                        }
                    else{return true;}
                }
        }
        
        
        
        
        return false;
    }
    
    boolean adash(){
        if(objdef()){
           
            return true;
        }
        if(array()){
           
            return true;
        }
        return false;
    }
    
    
    public boolean re(){
        if(e()){
            
            if(redash()){
                
                return true;
            }
        }
        return false;
    }
    
    public boolean redash(){
            
            String a1[] = {"||","&&",")","<",">",">=","<=","!=","==",";",","};
            for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).valuePart.equals(a1[i])){
                    if(arrayList.get(index).classPart.equals("RelationalOperator")){
                    index++;//RelationalOperator
                        if(e()){
                
                            if(redash()){
                    
                            return true;
                            }
                        }
                    }
                    else{return true;}
                }
            }
            
        
           
        
        return false;
    }
    
    public boolean e(){
        if(t()){
           
            if(edash()){
                
                return true;
            }
        }
        return false;
    }
    
    public boolean edash(){
        String a1[] = {"||","&&",")","<",">",">=","<=","!=","==","+","-",";",","};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).valuePart.equals(a1[i])){
                    if(arrayList.get(index).classPart.equals("additive")){
                        operator=arrayList.get(index).valuePart;
                    index++;
                        if(t()){
                
                            if(edash()){
                   
                                return true;
                            }
                        }
                        
                    }
                    else{return true;}
                }
        }
        
        
        
        
        
           
            
        
        return false;
    
    
   
    }
    
    public boolean t(){
        if(f()){
            
            if(tdash()){
               
                return true;
            }
        }
        return false;
    }
    
    boolean tdash(){
        String a1[] = {"||","&&",")","<",">",">=","<=","!=","==","+","-","*","/",";",","};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).valuePart.equals(a1[i])){
                    if(arrayList.get(index).classPart.equals("multiplicative")){
                        operator=arrayList.get(index).valuePart;
                        
                    index++;//multiplicative
                        if(f()){
                            
                            
                            String[] arrOfStr = type.split(",");
                            
//                           for(int u=0;u<arrOfStr.length;u++){
//                               System.out.println(arrOfStr[u]);
//                           }
//                            System.out.println(arrOfStr[3]);    
//                            System.out.println(arrOfStr[1]); 
                            type = compatibility(arrOfStr[0],arrOfStr[1],operator);
                            if(type.equals("")) System.out.println("Incompatible type");
                            
                            
                            
                            
                            
                            if(tdash()){
                
                            return true;
                            }
                        }
                    }
                    else{return true;}
                }
        }
        
        
        
        return false;
    }
    
    boolean f(){
        if(arrayList.get(index).valuePart.equals("!")){         
                index++;
                if(f()){
                
                return true;
            }
            }
        if(cons()){
            
            return true;
        }
        if(arrayList.get(index).classPart.equals("(")){
            index++;
            if(oe()){
            
            if(arrayList.get(index).classPart.equals(")")){
            index++;
            return true;
        }
        }
        }
        
        if(arrayList.get(index).classPart.equals("Identifier")){
            type=arrayList.get(index).classPart+","+type;
            index++;
            if(ref()){
          
            return true;
        }
        }
        return false;
    }
    
    boolean constructor(){
        
        if(arrayList.get(index).classPart.equals("(")){
            type_of_data_table="";
            index++;
            if(pl()){
            
            if(arrayList.get(index).classPart.equals(")")){
            type_of_data_table=type_of_data_table.replace(",", "/");
            if (insert_of_main_table_is_true==true) insert_class_table(name_of_data_table, type_of_data_table, access_modifier_of_datable, type_modifier_of_data_table,  name_of_class);    
            index++;
            if(arrayList.get(index).classPart.equals("{")){
            index++;
            if(mst()){
            
            if(arrayList.get(index).classPart.equals("}")){
            index++;
            return true;
        }
        }
        }
        }
        }
        }
        return false;
    }
    
    boolean pc(){
        String a1[] = {")","floatConstant","charConstant","StringConstant","newClass","Identifier"};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(constant1()){
            
                        if(p()){
           
                            return true;
                        }
                    }
                    else{return true;}
                }
        }
        
        
        
        
        
        return false;
    }
    
    boolean p(){
        
        String a1[] = {")",","};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).valuePart.equals(a1[i])){
                    if(arrayList.get(index).classPart.equals(",")){
                        index++;
                            if(ht()){
            
                                return true;
                                }
                            }
                    else{return true;}
                    }
        }
        
        
        
        
        return false;
    }
    
    boolean ht(){
        if(constant1()){
            
            if(p()){
          
            return true;
        }
        }
        return false;
    }
    
    boolean throw1(){
        if(arrayList.get(index).classPart.equals("throwClass")){             
            index++;
            if(nd()){
            
            return true;
        }
        }
        return false;
    }
    
    boolean nd(){
        if(arrayList.get(index).classPart.equals("Identifier")){
            index++;
            if(arrayList.get(index).classPart.equals(";")){
            index++;
            return true;
        }
        }
        if(arrayList.get(index).classPart.equals("newClass")){
            index++;
            if(exception1()){
            
            if(arrayList.get(index).classPart.equals("(")){
            index++;
            if(arrayList.get(index).classPart.equals("StringConstant")){
            index++;
            if(arrayList.get(index).classPart.equals(")")){
            index++;
            if(arrayList.get(index).classPart.equals(";")){
            index++;
            return true;
        }
        }
        }
        }
        }
        }
        return false;
    }
    
    boolean assignstforstate(){
        if(assignop()){
            
            if(oe()){
            
            return true;
        }
        }
        return false;
    }
    
    boolean assigop(){
        if(arrayList.get(index).classPart.equals("assignment")){
            index++;
            return true;
        }
        return false;
    }
    
    boolean incdecforforstat(){
        if(incdec()){
            
            return true;
        }
        return false;
    }
    
    boolean incdec(){
        if(arrayList.get(index).classPart.equals("additive")){
            index++;
            return true;
        }
        return false;
    }
    
    boolean ref(){
         
        
        if(alt()){
            
            return true;
        }
        return false;
    }
    
    boolean cons(){
        if(arrayList.get(index).classPart.equals("floatConstant") || arrayList.get(index).classPart.equals("charConstant") || arrayList.get(index).classPart.equals("StringConstant")){
            type=arrayList.get(index).classPart+","+type;
            
            //type=type+","+arrayList.get(index).classPart;
            index++;
            return true;
        }
        if(arrayList.get(index).classPart.equals("Identifier")){
            type=arrayList.get(index).classPart+","+type;
            index++;
            if(ref()){
                return true;
            }
        }
        
        return false;
    }
    
    
    
    boolean alt(){
        if(pk()){
            
            return true;
        }
        if(fc()){
           
            if(pk()){
           
            return true;
        }
        }
        if(arc()){
            
            if(pk()){
            
            return true;
        }
        }
        return false;
    }
    
    boolean pk(){
        
        String a1[] = {";","assignment","additive","multiplicative",")",".","mode"};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(arrayList.get(index).classPart.equals(".")){
            index++;
            if(arrayList.get(index).classPart.equals("Identifier")){
            index++;
            if(ref()){
            
            return true;
        }
        }
        }
                    
                    else{return true;}
                }
        }
        
        
        
        
        return false;
    }
    
    boolean assignst(){
        if(assigop()){
            
            if(oe()){
            
            if(arrayList.get(index).classPart.equals(";")){
            index++;
            return true;
        }
        }
        }
        return false;
    }
    
    
    
    boolean assignst1(){
        if(assigop()){
            
            if(oe()){
            
            return true;
        }
        }
        return false;
    }
    
    
    
    
    
    
    
    
    
    
    
    boolean try1(){
        if(arrayList.get(index).valuePart.equals("try")){             
            index++;
            if(arrayList.get(index).classPart.equals("{")){
            index++;
            if(mst()){
            
            if(arrayList.get(index).classPart.equals("}")){
            index++;
            if(arrayList.get(index).classPart.equals("catchClass")){                                                             
            index++;
            if(arrayList.get(index).classPart.equals("(")){
            index++;
            if(exe()){
         
            if(arrayList.get(index).classPart.equals(")")){
            index++;
            if(arrayList.get(index).classPart.equals("}")){
            index++;
            if(body()){
            
            if(arrayList.get(index).classPart.equals("}")){
            index++;
            if(barbq()){
            
            if(finally1()){
            
        }
        }
        }
        }
        }
        }
        }
        }
        }
        }
        }
        }
        }

        return false;

    }
    
    boolean exe(){
        if(exeptions()){
            
            if(exedash()){
            
            return true;
        }
        }
        return false;
    }
    
    boolean exedash(){
        if(arrayList.get(index).classPart.equals("Identifier")){
            index++;
            return true;
        }
        return false;
    }
    
    boolean finally1(){
        String a1[] = {"finallyClass","returnClass","breakContinueClass","doClass","ifClass","foreachClass","whileClass","tryClass","throwClass","additive","superClass","Identifier","datatype","thisClass"};
        
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(arrayList.get(index).valuePart.equals("finally")){          
            index++;
            if(arrayList.get(index).classPart.equals("{")){
            index++;
            if(mst()){
            
            if(arrayList.get(index).classPart.equals("}")){
            index++;
            return true;
        }
        }
        }
        }
                    else{return true;}
                }
        }
        
        
        
        
        
        return false;
    }
    
    boolean exeptions(){
        if(arrayList.get(index).classPart.equals("Identifier")){
            index++;
            return true;
        }
        return false;
    }
    
    boolean barbq(){
        
        String a1[] = {"tryClass","finallyClass","returnClass","breakContinueClass","doClass","ifClass","foreachClass","whileClass","tryFinalClass","throwClass","catchClass","additive","superClass","Identifier","datatype","thisClass"};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(arrayList.get(index).classPart.equals("catchClass")){          
            index++;
            if(exe()){
            
            if(arrayList.get(index).classPart.equals("}")){
            index++;
            if(mst()){
            
            if(arrayList.get(index).classPart.equals("}")){
            index++;
        }
        }
        }
        }
        }
            else{return true;}        
                    
                }
        }
        
        
        
        
        return false;
    }
    
    boolean arc(){
        if(arrayList.get(index).classPart.equals("[")){
            index++;
            if(xdashdash()){
           
            return true;
        }
        }
        return false;
    }
    
    boolean xdashdash(){
        if(arrayList.get(index).classPart.equals("floatConstant")){
            index++;
            if(arrayList.get(index).classPart.equals("]")){
            index++;
            return true;
        }
        }
        if(arrayList.get(index).classPart.equals("Identifier")){
            index++;
            if(arrayList.get(index).classPart.equals("]")){
            index++;
            return true;
        }
        }
        return false;
    }
    
    boolean fc(){
        if(arrayList.get(index).classPart.equals("(")){
            index++;
            if(pc()){
            
            if(arrayList.get(index).classPart.equals(")")){
            index++;
            return true;
        }
        }
        }
        return false;
    }
    
    boolean fam(){
        if(abstract1()){
            
            if(rettype()){
            
            if(arrayList.get(index).classPart.equals("Identifier")){
            index++;
            if(arrayList.get(index).classPart.equals("(")){
            index++;
            if(arrayList.get(index).classPart.equals(")")){
            index++;
            if(arrayList.get(index).classPart.equals(";")){
            index++;
            return true;
        }
        }
        }
            
        }
        }
        }
        return false;
    }
    
    boolean k(){
        if(arrayList.get(index).classPart.equals("newClass")){
            index++;
            if(arrayList.get(index).classPart.equals("Identifier")){
            index++;
            if(arrayList.get(index).classPart.equals("(")){
            index++;
            if(pc()){
          
            if(arrayList.get(index).classPart.equals(")")){
            index++;
            if(ref()){
            
            return true;
        }
        }
        }
        }
        }
        }
        return false;
    }
    
    boolean ifelse1(){
            if(arrayList.get(index).classPart.equals("ifClass")){  
            index++;
            if(arrayList.get(index).classPart.equals("(")){
            index++;
            if(oe()){
           
            if(arrayList.get(index).classPart.equals(")")){
            index++;
            if(body()){
            
            if(else1()){
           
            return true;
        }
        }
        }
        }
        }
        }
    
    
    return false;
    
}
    boolean else1(){
        String a1[] = {"returnClass","breakContinueClass","doClass","ifClass","foreachClass","whileClass","tryClass","throwClass","additive","superClass","Identifier","datatype","thisClass", "finallyClass", "elseClass"};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(arrayList.get(index).classPart.equals("elseClass")){              
            index++;
            if(body()){
           
            return true;
        }
        }
                    else{return true;}
                }
        }
        
        
        
        
        
        
        return false;
    }
    
    boolean pl(){
        String a1[] = {")","Identifier","datatype"};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(bulb()){
            if(arrayList.get(index).classPart.equals("Identifier")){
            index++;
            if(p1()){
           
            if(pl1()){
            
            return true;
        }
        }
        }
        }
                    else{ 
                        return true;}
                }
        }
        
        
        
        
        
        
    return false;
        
}

    boolean p1(){
        String a1[] = {"assignment","[",",",")"};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(arrayList.get(index).classPart.equals("[")){
            index++;
            if(arrayList.get(index).classPart.equals("]")){
            index++;
            return true;
        }
        }
                    else{return true;}
                }
        }
        
        
        
        
        return false;
    }
    
    boolean pl1(){
        if(arrayList.get(index).classPart.equals("assignment")){             
            index++;
            if(oe()){
            
            if(pl2()){
            
            return true;
        }
        }
        }
        if(pl2()){
            
            return true;
        }
        
        return true;
    }
    
    boolean pl2(){
        String a1[] = {")",","};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(arrayList.get(index).classPart.equals(",")){
            index++;
            if(pl()){
         
            return true;
        }
        }
                    else {return true;}
                }
        }
        
        
        
        
        
        return false;
    }
    
    boolean bulb(){
        String dt_tempory = type_of_data_table;
        if(arrayList.get(index).classPart.equals("Identifier")){
                       // type_of_data_table = arrayList.get(index).valuePart +","+ type_of_data_table;
            type_of_data_table = type_of_data_table +","+ arrayList.get(index).valuePart;
            index++;
            return true;
        }
        if(dt()){
            type_of_data_table = dt_tempory +","+ type_of_data_table;
            return true;
        }
        type_of_data_table = dt_tempory +","+ "void";
        return false;
    }
    
    boolean cc(){
        if(incdec()){
           
           return true;
        }
        if(assignst()){
         
           return true;
        }
        return false;
    }
    
    
    
    boolean function(){
        if(arrayList.get(index).classPart.equals("(")){
            index++;
            if(pl()){
            
            if(arrayList.get(index).classPart.equals(")")){
                if(insert_of_main_table_is_true==true)insert_class_table(name_of_data_table, type_of_data_table, access_modifier_of_datable, type_modifier_of_data_table,  name_of_class);
            index++;
            if(throws1()){
           
            if(arrayList.get(index).classPart.equals("{")){
                create_scope();
            index++;
            if(mst()){
            destory_scope();    
            if(arrayList.get(index).classPart.equals("}")){
            index++;
            return true;
        }
        }
        }
        }
        }
        }
        }
        return false;
    }
    
    boolean exception1(){
        if(arrayList.get(index).classPart.equals("Identifier")){
            index++;
            return true;
        }
        return false;
    }
    
    boolean throws1(){
        String a1[] = {"{","throwsClass"};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    
                    if(arrayList.get(index).classPart.equals("throwsClass")){
            index++;
            if(exception1()){
           
            if(vdash()){
          
            return true;
        }
        }
        }
                    else{return true;}
                    
                    
                }
        }

        
        
        return false;
    }
    
    boolean vdash(){
        if(arrayList.get(index).classPart.equals(",")){
            index++;
            if(exception1()){
                
            }
        }
        return true;
    }
    
    boolean rettype(){
        if(arrayList.get(index).classPart.equals("voidClass")){                  
            index++;
            return true;
        }
        if(dt()){
           
            return true;
        }
        if(arrayList.get(index).classPart.equals("Identifier")){
            index++;
            return true;
        }
        return false;
    }
    
    boolean constant1(){
        //if(index=="intconst"){
          //  index++;
            //return true;
        //}
        if(arrayList.get(index).classPart.equals("StringConstant")){
            index++;
            return true;
        }
        if(arrayList.get(index).classPart.equals("charConstant")){
            index++;
            return true;
        }
        if(arrayList.get(index).classPart.equals("floatConstant")){
            index++;
            return true;
        }
        //if(index=="charCons"){
          //  index++;
            //return true;
        //}
        if(k()){
            return true;
        }
        if(arrayList.get(index).classPart.equals("Identifier")){
            index++;
            if(ref()){
           
            return true;
        }
        }
        return false;
    }
    
    boolean dowhile(){
        if(arrayList.get(index).classPart.equals("doClass")){
            index++;
            if(arrayList.get(index).classPart.equals("{")){
            create_scope();
            index++;
            if(semicolon()){
            
            if(mst()){
            destory_scope();    
            if(arrayList.get(index).classPart.equals("}")){
                
            index++;
            if(arrayList.get(index).classPart.equals("whileClass")){        
            index++;
            if(arrayList.get(index).classPart.equals("(")){
            index++;
            if(oe()){
            
            if(arrayList.get(index).classPart.equals(")")){
            index++;
            if(arrayList.get(index).classPart.equals(";")){
            index++;
            return true;
           
        }
           
        }
           
        }
           
        }
           
        }
           
        }
           
        }
           
        }
        }
        }
        return false;
    }
    
    boolean semicolon(){
        String a1[] = {";","}","returnClass","breakContinueClass","doClass","ifClass","foreachClass","whileClass","tryClass","additive","superClass","Identifier","datatype","thisClass", "tryClass","throwClass"};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(arrayList.get(index).classPart.equals(";")){
            index++;
            if(semicolon()){
            
            return true;
        }
        }
                    else{return true;}
                }
        }
        
        
        
        
        
        
        return false;
    }
    
    boolean return1(){
        if(arrayList.get(index).classPart.equals("returnClass")){
            index++;
            if(oe()){
           
            if(arrayList.get(index).classPart.equals(";")){
            index++;
            return true;
        }
        }
        }
        return false;
    }
    
    boolean break1(){
        if(arrayList.get(index).classPart.equals("breakContinueClass")){         
            index++;
            if(arrayList.get(index).classPart.equals(";")){
            index++;
            return true;
        }
        }
        return false;
    }
    
    boolean continou1(){
        if(arrayList.get(index).classPart.equals("breakContinueClass")){                       
            index++;
            if(arrayList.get(index).classPart.equals(";")){
            index++;
            return true;
        }
        }
        return false;
    }
    
    boolean for1(){
        if(arrayList.get(index).classPart.equals("foreachClass")){
            
            index++;
            if(arrayList.get(index).classPart.equals("(")){
                index++;
            if(c1()){
            
            if(c2()){
            index++;
            if(arrayList.get(index).classPart.equals(";")){
            index++;
            if(c3()){
            
            if(arrayList.get(index).classPart.equals(")")){
            index++;
            if(body()){
           
            return true;
        }
        }
        }
        }
        }
        }
        }
        }
        return false;
    }
    
    
    
    
    boolean c1(){
        if(arrayList.get(index).valuePart.equals(";")){
            return true;
        }
        if(declarforc1()){
            return true;
        }
        if(assignstforc1()){
            return true;
        }
        return false;
    }
    
    boolean c2(){
        String a1[] = {";","!","Identifier","charConstant","floatConstant","StringConstant"};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(oe()){
           
                return true;
        }
                    else{return true;}
                }
        }
        
        
        
        
        
        return false;
    }
    
    boolean c3(){
        
        
        String a1[] = {")","superClass","thisClass","Identifier","additive"};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(arrayList.get(index).classPart.equals("Identifier")){
            index++;
            if(ref()){
            if(bdash()){
           
            return true;
        }
            }
        }
        
        if(arrayList.get(index).classPart.equals("additive")){
            index++;
            if(arrayList.get(index).classPart.equals("additive")){
            index++;
            if(ts()){
            
            if(arrayList.get(index).classPart.equals("Identifier")){
            index++;
            if(ref()){
            
            return true;
        }
        }
        }
        }
        }
        if(ru()){
            
            if(arrayList.get(index).classPart.equals("Identifier")){
            index++;
            if(ref()){
           
            if(bdash()){
            
            return true;
        }
        }
        }
        }
        else{return true;}
                }
        }
        
        
        
        
        
        
        
        
        return false;
    }
    
    boolean bdash(){
        if(assignstforstate()){
            
            return true;
        }
        if(arrayList.get(index).classPart.equals("additive")){
            index++;
            if(kdash()){
          
            return true;
        }
        }
        if(arrayList.get(index).valuePart.equals("*")){
            index++;
            if(pdash()){
            
            return true;
        }
        }
        if(arrayList.get(index).valuePart.equals("/")){
            index++;
            if(pdash()){
            
            return true;
        }
        }
        if(arrayList.get(index).valuePart.equals("%")){
            index++;
            if(pdash()){
            
            return true;
        }
        }
        
        return false;
    }
    
    boolean kdash(){
        if(incdecforforstat()){
           
            return true;
        }
        if(assignstforstate()){
            
            return true;
        }
        return false;
    }
    
    boolean pdash(){
        if(assignstforstate()){
            
            return true;
        }
        return false;
    }
    
    boolean declarforc1(){
        if(dt()){
            
            if(arrayList.get(index).classPart.equals("Identifier")){
            index++;
            if(initial()){
            
            if(list1()){
            
            return true;
        }
        }
        }
        }
        return false;
    }
    
    boolean initial1(){
        String a1[] = {";",",","assignment"};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(arrayList.get(index).classPart.equals(";")){
            index++;
            if(oe()){
            
            return true;
        }
        }
                    else{return true;}
                }
        }
        
        
        
        
        return false;
    }
    
    boolean list1(){
        if(arrayList.get(index).classPart.equals(";")){
            index++;
            return true;
        }
        if(arrayList.get(index).classPart.equals(",")){
            index++;
            if(arrayList.get(index).classPart.equals("Identifier")){
            index++;
            if(initial1()){
           
            if(list1()){
            
            return true;
        }
        }
        }
        }
        return false;
    }
    
    boolean assignstforc1(){
        if(ts()){
            
            if(arrayList.get(index).classPart.equals("Identifier")){
            index++;
            if(ref()){
            
            if(assignop()){
            
            if(oe()){
            
            if(arrayList.get(index).classPart.equals(";")){
            index++;
            return true;
        }
        }
        }
        }
        }
        }
     return false;   
    }
    
    boolean assignop(){
        if(arrayList.get(index).classPart.equals("assignment")){    
            index++;
            return true;
        }
        if(compoundassign()){
            
            return true;
        }
        return false;
    }
    
    boolean compoundassign(){
        if(arrayList.get(index).classPart.equals("assignment")){
            index++;
            return true;
        }
        if(arrayList.get(index).classPart.equals("AssignmentOperator")){
            index++;
            return true;
        }
        /*if(arrayList.get(index).classPart.equals("assignment")){
            index++;
            return true;
        }
        if(arrayList.get(index).classPart.equals("assignment")){
            index++;
            return true;
        }
        if(index=="%="){
            index++;
            return true;
        }*/
        return false;
    }
    
    public boolean body(){
        if(arrayList.get(index).classPart.equals(";")){
            index++;
            return true;
        }
        if(sst()){
           
            return true;
        }
        if(arrayList.get(index).classPart.equals("{")){
            index++;
            if(mst()){
            
            if(arrayList.get(index).classPart.equals("}")){
            index++;
            return true;
        }
        }
        }
        return false;
    }
    
    boolean mst(){
        String a1[] = {";","}","returnClass","breakContinueClass","doClass","ifClass","foreachClass","whileClass","tryClass","throwClass","additive","superClass","Identifier","datatype","thisClass"};
        if(arrayList.get(index).classPart.equals("}")){   return true;      }
            
            
            
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(sst()){
            
            if(mst()){
            
            return true;
        }
        }
                    // major change in cfg
                    if(arrayList.get(index).classPart.equals(";")){
                        index++;
                        if(mst()){
                            return true;
                        }
                    }
                    // major change in cfg
                    
                    
                    
                    else{return false;}
                }
        }
        
        
        
        
        
        return false;
    }
    
//    ArrayList<main_table> main_table_arraylist=new ArrayList<main_table>(); 
//    String name_of_class = "";
//    String type_of_class = "";
//    String access_modifier_of_class = "";
//    String catagory_of_class = "";
//    String parent = "";
    
    
    
    boolean final1(){
        if(arrayList.get(index).classPart.equals("finalClass")){
            catagory_of_class=arrayList.get(index).valuePart;
            index++;
            return true;
        }
        return false;
    }
    
    boolean final2(){
        
        String a1[] = {"Identifier","datatype","finalClass"};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(arrayList.get(index).classPart.equals("finalClass")){
                        catagory_of_class=arrayList.get(index).valuePart;
            index++;
            return true;
        }
                    else {return true;}
                }
        }

        
        
        
        
        
        return false;
    }
    
    boolean while1(){
        if(arrayList.get(index).classPart.equals("whileClass")){
            index++;
            if(arrayList.get(index).classPart.equals("(")){
            index++;
            if(oe()){
            
            if(arrayList.get(index).classPart.equals(")")){
            index++;
            if(body()){
            
            return true;
        }
        }
        }
        }
        }
        return false;
    }
    
    boolean declare(){
        if(initial()){
            
            if(list()){
            
            return true;
        }
        }
        return false;
    }
    
    
//String name_of_data_table = "";
//    String type_of_data_table = "";
//    String access_modifier_of_data_table = "";
//    String type_modifier_of_data_table = "";
//    String link = "";
    
    boolean initial(){
        
        String a1[] = {";",",","assignment"};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    
                    if(arrayList.get(index).classPart.equals("assignment")){
            index++;
            if(oe()){
                        type = compatibility(type,type_of_data_table,operator);
                            if(type.equals("")) System.out.println("Incompatible type");
                            type="";
            return true;
        }
        }
                    else { 
                        if(insert_of_main_table_is_true==true)insert_function_table(name_of_function_table ,type_of_funtion_table ,stack.peek());
                        if(insert_of_main_table_is_true==true)insert_class_table(name_of_data_table, type_of_data_table, access_modifier_of_datable, type_modifier_of_data_table,  name_of_class);
                        return true;}
                    
                }
        }
        
        
        
        
        
        return false;
    }
    
    boolean list(){
        if(arrayList.get(index).classPart.equals(";")){
            index++;
            return true;
        }
        if(arrayList.get(index).classPart.equals(",")){
            index++;
            if(arrayList.get(index).classPart.equals("Identifier")){
                index++;
                if(initial()){
                
                if(list()){
                
            }
            }
            }
        }
        return false;
    }
    
    boolean ts(){
        
        String a1[] = {"superClass","thisClass","Identifier"};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(arrayList.get(index).classPart.equals("thisClass")){                   
            index++;
            if(arrayList.get(index).classPart.equals(".")){
                index++;
            return true;
            }
        }
        if(arrayList.get(index).classPart.equals("superClass")){                   
            index++;
            if(arrayList.get(index).classPart.equals(".")){
                index++;
            return true;
            }
        }
        else{return true;}
                }
        }
        
        
        
        
        return false;
    }
    
    boolean dt(){
        //if(index=="int"){
          //      index++;
            //    return true;
            //}
        if(arrayList.get(index).classPart.equals("datatype")){
                type_of_data_table = arrayList.get(index).valuePart;
                type_of_funtion_table = arrayList.get(index).valuePart;
                index++;
                return true;
            }
        
        return false;
    }
    
    boolean abstract1(){
        if(arrayList.get(index).classPart.equals("abstractClass")){
                index++;
                return true;
            }
        return false;
    }
    
    boolean multipleclassname(){
        if(arrayList.get(index).classPart.equals("Identifier")){
            
                List<Object> lookup_main_table_returntype=lookup_main_table(arrayList.get(index).valuePart);
                if(insert_of_main_table_is_true==true && lookup_main_table_returntype.get(0).equals("")){
                    System.out.println("Undeclared");
                }
                if(insert_of_main_table_is_true==true && !lookup_main_table_returntype.get(0).equals("interface")){
                    System.out.println("It should be interface");
                }
            
                parent  = parent + "," + arrayList.get(index).valuePart;
                
                
                
                index++;
                if(d()){
                //insert_main_table(name_of_class,type_of_class ,access_modifier_of_class ,catagory_of_class ,parent);
                return true;
            }
            }
        return false;
    }
    
    boolean d(){
        
        String a1[] = {"{",","};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(arrayList.get(index).classPart.equals(",")){
                index++;
                if(arrayList.get(index).classPart.equals("Identifier")){
                    
                    List<Object> lookup_main_table_returntype=lookup_main_table(arrayList.get(index).valuePart);
                if(insert_of_main_table_is_true==true && lookup_main_table_returntype.get(0).equals("")){
                    System.out.println("Undeclared");
                }
                if(insert_of_main_table_is_true==true && !lookup_main_table_returntype.get(0).equals("interface")){
                    System.out.println("It should be interface");
                }    
                    parent  = parent + "," + arrayList.get(index).valuePart;
                index++;
                if(d()){
                
              
                return true;
            }
            }
            }
                    else{ insert_of_main_table_is_true=insert_main_table(name_of_class,type_of_class ,access_modifier_of_class ,catagory_of_class ,parent);
                        return true;}
                }
        }
        
        
        return false;
    }
    
    boolean inheritance(){
        String a1[] = {"{","extendsClass","implementClass"};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(arrayList.get(index).classPart.equals("extendsClass")){
                index++;
                if(arrayList.get(index).classPart.equals("Identifier")){
                parent = arrayList.get(index).valuePart;
                List<Object> lookup_main_table_returntype=lookup_main_table(parent);
                if(insert_of_main_table_is_true==true && lookup_main_table_returntype.get(0).equals("")){
                    System.out.println("Undeclared");
                }
                if(insert_of_main_table_is_true==true && lookup_main_table_returntype.get(0).equals("class") && lookup_main_table_returntype.get(1).equals("final")){
                    System.out.println("class can'not be inherited");
                }
                if(insert_of_main_table_is_true==true && lookup_main_table_returntype.get(0).equals("interface")){
                    System.out.println("only class extends");
                }
                index++;
                if(in()){
                
                return true;
            }
            }
            }
                    if(in()){
                        return true;
                    }
                    
                   
                    else{  insert_of_main_table_is_true=insert_main_table(name_of_class,type_of_class ,access_modifier_of_class ,catagory_of_class ,parent);
                           return true;}
                }
        }
        
        
        
        
        
        
        
        return false;
    }
    
    boolean in(){
        
        String a1[] = {"{","implementClass"};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(arrayList.get(index).classPart.equals("implementClass")){
                index++;
                if(multipleclassname()){
                
                return true;
            }
            }
                    else {  insert_of_main_table_is_true=insert_main_table(name_of_class,type_of_class ,access_modifier_of_class ,catagory_of_class ,parent);
                        return true;}
                }
        }
        
        
        
        
        
        return false;
    }
    
    boolean objdef(){
        if(huu()){
                
                return true;
            }
        return false;
    }
    
    boolean huu(){
        if(arrayList.get(index).classPart.equals("assignment")){
            index++;
        if(arrayList.get(index).classPart.equals("newClass")){
            index++;
            if(arrayList.get(index).classPart.equals("Identifier")){
                
                
                List<Object> lookup_main_table_returntype=lookup_main_table(arrayList.get(index).valuePart);
                if(insert_of_main_table_is_true==true && lookup_main_table_returntype.get(0).equals("")){
                    System.out.println("error at new keyword (not declared)");
                }
                
//                String parent_spliting = (String) lookup_main_table_returntype.get(2);
//                String[] a = parent_spliting.split(",");
//                for(int i=0; i<a.length;i++){
//                    
//                    if(insert_of_main_table_is_true==true && !lookup_main_table_returntype.get(0).equals("") && !a[i].equals(type_of_data_table)){
//                    //insert_class_table(name_of_data_table, type_of_data_table, access_modifier_of_datable, type_modifier_of_data_table,  name_of_class);
//                    if(!arrayList.get(index).valuePart.equals(type_of_data_table)){
//                        System.out.println("error at new keyword");
//                    }
//                
//                }
//                    
//                }
                
                
                if(insert_of_main_table_is_true==true && !lookup_main_table_returntype.get(0).equals("") && !lookup_main_table_returntype.get(2).equals(type_of_data_table)){
                    //insert_class_table(name_of_data_table, type_of_data_table, access_modifier_of_datable, type_modifier_of_data_table,  name_of_class);
                    if(!arrayList.get(index).valuePart.equals(type_of_data_table)){
                        System.out.println("error at new keyword");
                    }
                
                }
//                String current = arrayList.get(index).valuePart;
//                String ty=type_of_data_table;
//                int i=0;
//                for(i=0; i<main_table_arraylist.size();i++){
//                    List<Object> loo_cuur=lookup_main_table(current);
//                    List<Object> loo_ty=lookup_main_table(ty);
//                    if(current.equals(arrayList.get(index).valuePart)) break;
//                    if(loo_cuur.get(0).equals(""))  System.out.println("errorr");
//                    if(loo_ty.get(2).equals(current)) System.out.println("errorr");
//                    ///if(list.get(2).equals(type_of_data_table))  break;
//                    
//                    //if(list.get(0).equals(""))  System.out.println("errorr");
//                    
////                    par = (String) list.get(2);
//                    
//                    
//                }
//                if(i==main_table_arraylist.size()) System.out.println("error");
                
                
                
                
                
            index++;
            if(arrayList.get(index).classPart.equals("(")){
            index++;
            if(pl()){
           
            if(arrayList.get(index).classPart.equals(")")){
            index++;
            if(arrayList.get(index).classPart.equals(";")){
                
            index++;
            return true;
        }
        }
        }
        }
        }
        }
        }
        if(arrayList.get(index).classPart.equals(";")){
            if(!type_of_data_table.equals("")){
                List<Object> lookup_main_table_returntype=lookup_main_table(type_of_data_table);
                if(insert_of_main_table_is_true==true && lookup_main_table_returntype.get(0).equals("")){
                    System.out.println("Undeclared");
                }
                if(insert_of_main_table_is_true==true && lookup_main_table_returntype.get(3).equals("private")){
                    System.out.println("Cannot make declare because it is private");
                }
                if( !lookup_main_table_returntype.get(3).equals("private") && !lookup_main_table_returntype.get(0).equals("")){
                    if(insert_of_main_table_is_true==true)insert_class_table(name_of_data_table, type_of_data_table, access_modifier_of_datable, type_modifier_of_data_table,  name_of_class);

                }
                
            }
            if(!type_of_funtion_table.equals("")){
                List<Object> lookup_main_table_returntype=lookup_main_table(type_of_funtion_table);
                if(insert_of_main_table_is_true==true &&  lookup_main_table_returntype.get(0).equals("")){
                    System.out.println("Undeclared");
                }
                if(insert_of_main_table_is_true==true && lookup_main_table_returntype.get(3).equals("private")){
                    System.out.println("Cannot object make declare because it is private");
                }
                if(!lookup_main_table_returntype.get(3).equals("private") && !lookup_main_table_returntype.get(0).equals("")){
                    if(insert_of_main_table_is_true==true)insert_function_table(name_of_function_table ,type_of_funtion_table ,stack.peek());
                }
                
                
            }    
            
            
            index++;
            return true;
        }
        return false;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    boolean array1(){
        if(hu1()){
            
            return true;
        }
        return false;
    }
    
  
    
    boolean hu1(){
        if(arrayList.get(index).classPart.equals("[")){
            index++;
            if(arrayList.get(index).classPart.equals("]")){
            index++;
            if(kf1()){
            
            return true;
        }
        }
        }
        return false;
    }
    
    boolean kf1(){
        if(arrayList.get(index).classPart.equals(";")){
            index++;
            return true;
        }
        if(arrayList.get(index).classPart.equals("assignment")){
            index++;
            if(il1()){
            
            return true;
        }
        }
        return false;
    }
    
    boolean il1(){
        if(arrayList.get(index).classPart.equals("{")){
            index++;
            if(am1()){
            
            if(arrayList.get(index).classPart.equals("}")){
            index++;
            if(arrayList.get(index).classPart.equals(";")){
            index++;    
            return true;
        }
        }
        }
        }
        if(arrayList.get(index).classPart.equals("newClass")){
            index++;
            if(arrayList.get(index).classPart.equals("datatype")){
            index++;
            if(arrayList.get(index).classPart.equals("[")){
            index++;
            if(arrayList.get(index).classPart.equals("floatConstant")){
            index++;
            if(arrayList.get(index).classPart.equals("]")){
            index++;
            if(arrayList.get(index).classPart.equals(";")){
            index++;
            return true;
        }
        }
        }
        }
        }
        }
        return false;
    }
    
    boolean am1(){
        
        String a1[] = {"}","new","Identifier","charConstant","floatConstant","StringConstant"};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(constant1()){
           
            if(uml()){
            
            return true;
        }
        }
                    else{return true;}
                }
        }
        
        
        
        
        
        return false;
    }
    
    boolean uml1(){
        String a1[] = {",","}"};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(arrayList.get(index).classPart.equals(",")){
            index++;
            if(moll1()){
            
            return true;
        }
        }
                    else{return true;}
                }
        }
        
        
        
        
        return false;
    }
    
    boolean moll1(){
        if(constant1()){
           
            if(uml1()){
            
            return true;
        }
        }
        return false;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    boolean array(){
        if(hu()){
            
            return true;
        }
        return false;
    }
    
    boolean tt(){
        if(assignst()){
          
           return true;
        }
        return false;
    }
    
    boolean hu(){
        if(arrayList.get(index).classPart.equals("[")){
            index++;
            if(arrayList.get(index).classPart.equals("]")){
            index++;
            if(kf()){
            
            return true;
        }
        }
        }
        return false;
    }
    
    boolean kf(){
        if(arrayList.get(index).classPart.equals(";")){
            index++;
            return true;
        }
        if(arrayList.get(index).classPart.equals("assignment")){
            index++;
            if(il()){
            
            return true;
        }
        }
        return false;
    }
    
    boolean il(){
        if(arrayList.get(index).classPart.equals("{")){
            index++;
            if(am()){
            
            if(arrayList.get(index).classPart.equals("}")){
            index++;
            if(arrayList.get(index).classPart.equals(";")){
                index++;
            return true;
        }
        }
        }
        }
        if(arrayList.get(index).classPart.equals("newClass")){
            index++;
            if(arrayList.get(index).classPart.equals("Identifier")){
            index++;
            if(arrayList.get(index).classPart.equals("[")){
            index++;
            if(arrayList.get(index).classPart.equals("floatConstant")){
            index++;
            if(arrayList.get(index).classPart.equals("]")){
            index++;
            if(arrayList.get(index).classPart.equals(";")){
            index++;
            return true;
        }
        }
        }
        }
        }
        }
        return false;
    }
    
    boolean am(){
        
        String a1[] = {"}","new","Identifier","charConstant","floatConstant","StringConstant"};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(constant1()){
           
            if(uml()){
            
            return true;
        }
        }
                    else{return true;}
                }
        }
        
        
        
        
        
        return false;
    }
    
    boolean uml(){
        String a1[] = {",","}"};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(arrayList.get(index).classPart.equals(",")){
            index++;
            if(moll()){
            
            return true;
        }
        }
                    else{return true;}
                }
        }
        
        
        
        
        return false;
    }
    
    boolean moll(){
        if(constant1()){
           
            if(uml()){
            
            return true;
        }
        }
        return false;
    }
    
    boolean enuminher(){
        
        String a1[] = {"{","implementClass"};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(arrayList.get(index).classPart.equals("implementClass")){
            index++;
            if(arrayList.get(index).classPart.equals("Identifier")){
            index++;
            if(bag()){
           
            return true;
        }
        }
        }
                    else{return true;}
                }
        }
        
        
        
        
        
        return false;
    }
    
    boolean bag(){
        
        String a1[] = {"{",","};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(arrayList.get(index).classPart.equals(",")){
            index++;
            if(arrayList.get(index).classPart.equals("Identifier")){
            index++;
            if(bag()){
            
            return true;
        }
        }
        }
                    else{return true;}
                }
        }
        
        
        
        
        return true;
    }
    
    boolean enumbody(){
        if(ai()){
            
            if(pro()){
           
            return true;
        }
        }
        return false;
    }
    
    boolean pro(){
        if(enumbody9()){
            
            return true;
        }
        return false;
    }
    
    boolean ai(){
        if(arrayList.get(index).classPart.equals("Identifier")){
            index++;
            if(xx()){
            if(arrayList.get(index).classPart.equals(";")){
                index++;
            return true;}
        }
        }
        
        return false;
    }
    
    boolean xx(){
        String a1[] = {";",",","("};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(arrayList.get(index).classPart.equals(",")){
            index++;
            if(arrayList.get(index).classPart.equals("Identifier")){
            index++;
            if(xx()){
          
            return true;
        }
        }
        }
        if(arrayList.get(index).classPart.equals("(")){
            index++;
            if(pc()){
            
            if(arrayList.get(index).classPart.equals(")")){
            index++;
            if(mob()){
           
            return true;
        }
        }
        }
        }
        else{return true;}
                }
        }
        
        
        
        
        
        return false;
    }
    
    boolean mob(){
        
        String a1[] = {";",","};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(arrayList.get(index).classPart.equals(",")){
            index++;
            if(arrayList.get(index).classPart.equals("Identifier")){
            index++;
            if(arrayList.get(index).classPart.equals("(")){
            index++;
            if(pc()){
           
            if(arrayList.get(index).classPart.equals(")")){
            index++;
            if(mob()){
            
        }
        }
        }
        }
        }
        }
                    else{return true;}
                }
        }
        
        
        
        
        
        return false;
    }
    
    boolean enumbody9(){
        String a1[] = {"}","accessModifier","finalClass","staticClass","Identifier","datatype"};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(arrayList.get(index).valuePart.equals("private")){
            index++;
            if(zz()){
            
            return true;
        }
        }
        if(arrayList.get(index).valuePart.equals("public")|| arrayList.get(index).valuePart.equals("protected") || arrayList.get(index).valuePart.equals("defualt") ){   //other than private
            index++;
            if(li()){
           
            return true;
        }
        }
        else{return true;}
                }
        }
        
        
        
        
        
        
        
        return false;
    }
    
    boolean li(){
        if(arrayList.get(index).classPart.equals("finalClass")){
            index++;
            if(h()){
           
            return true;
        }
        }
        if(arrayList.get(index).classPart.equals("staticClass")){      
            index++;
            if(final2()){
            
            if(h()){
           
            return true;
        }
        }
        }
        if(dt()){
            
            if(z() ){
           
            return true;
        }
        }
        if(arrayList.get(index).classPart.equals("Identifier")){
            index++;
            if(mdash()){
           
            return true;
        }
        }
        return false;
        
    }
    
    boolean mdash(){
        if(o()){
            
            return true;
        }
        return false;
    }
    
    boolean ru(){
        if(arrayList.get(index).classPart.equals("thisClass")){                   
            index++;
            if(arrayList.get(index).classPart.equals(".")){
                index++;
            return true;
            }
        }
        
        if(arrayList.get(index).classPart.equals("superClass")){                   
            index++;
            if(arrayList.get(index).classPart.equals(".")){
                index++;
            return true;
            }
        }
        return false;
    }
    
    boolean sst(){
    access_modifier_of_datable = "";
    name_of_data_table = "";
    type_of_data_table = "";
    type_modifier_of_data_table = "";
        if(while1()){
            
            return true;
        }
        if(for1()){
            
            return true;
        }
        if(ifelse1()){
            
            return true;
        }
        if(dowhile()){
            
            return true;
        }
       if(continou1()){
            
            return true;
        }
        if(break1()){
            
            return true;
        }
        if(return1()){
            
            return true;
        }
        if(throw1()){
            
            return true;
        }
        if(try1()){
            
            return true;
        }
        if(zzdash()){
            name_of_function_table ="";
                type_of_funtion_table = "";
            return true;
        }
        name_of_function_table ="";
                type_of_funtion_table = "";
        return false;
    }
        
    boolean zzdash(){
        copy_of_stack =  (Stack<Integer>) stack.clone();
        lookup_variable_for_function_table_to_class_table=name_of_class;
        if(dt()){
            
            if(zp()){
            
            return true;
        }
        }
        if(arrayList.get(index).classPart.equals("Identifier")){
            name_of_function_table =arrayList.get(index).valuePart;
            type_of_funtion_table = arrayList.get(index).valuePart;
            index++;
            if(xxx()){
            
            return true;
        }
        }
        if(ru()){
            if(arrayList.get(index).classPart.equals("Identifier")){
            if(ref1ref()){
            
            return true;
        }
            }
        }
        if(arrayList.get(index).classPart.equals("additive")){
            index++;
            if(arrayList.get(index).classPart.equals("additive")){
            index++;
            if(ts()){
            
            if(arrayList.get(index).classPart.equals("Identifier")){
            index++;
            if(ref()){
            
            if(arrayList.get(index).classPart.equals(";")){
            index++;
            return true;
        }
        }
        }
        }
        }
        }
        
        return false;
        
    }
    
    boolean odash(){
        if(arrayList.get(index).classPart.equals("Identifier")){
            name_of_function_table =arrayList.get(index).valuePart;
//            type_of_funtion_table = arrayList.get(index-1).valuePart;    // due to <zz'> terminal -> ID <XXX>
//            name_of_function_table  = arrayList.get(index).valuePart;
            index++;
            if(adash()){
            
            return true;
        }
        }
        return false;
    }
    
    
    boolean xxx(){
        if(odash()){
            
            return true;
        }
        if(ref1ref()){
//            name_of_function_table  = arrayList.get(index-1).valuePart;
            if(arrayList.get(index).classPart.equals(";")){
                
                stack = (Stack<Integer>) copy_of_stack.clone();
                copy_of_stack.clear();
            index++;
            return true;
        }
        }
        if(arrayList.get(index).classPart.equals("Identifier")){
            index++;
            if(gz()){
                return true;
            }
        }
        return false;
    }
    
    boolean ref1ref(){
        if(aj()){
            
            if(xr()){
            
            return true;
        }
        }
//        if(arrayList.get(index).classPart.equals("Identifier")){
//            index++;
//            if(ref()){
//                if(xxp()){
//                    if(arrayList.get(index).classPart.equals(";")){
//                        index++;
//                    return true;
//                    }
//                }
//                
//                
//                
//            }
//        }

//        if(arrayList.get(index).classPart.equals("Identifier")){
//            index++;
//            if(ref1ref()){
//                return true;
//            }
//        }
        
        return false;
    }
    
    boolean aj(){
        

    String a1[] = {"(","assignment","additive","multiplicative","mode",".","["};
    for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(c()){
                        
            return true;
        }
        if(arc()){
          
            if(c()){
           
            return true;
        }
        }
        else{return true;}
                }
        }
    
        
        
        
        
        
        
        
        
        return false;
    }
    
    boolean c(){
        String a1[] = {"(","assignment","additive","multiplicative","mode","."};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(arrayList.get(index).classPart.equals(".")){
                        
                        
                        //copy_of_stack =  (Stack<Integer>) stack.clone();
                        List<Object> lookup_function_table_returntype=lookup_function_table(name_of_function_table,stack.peek());
//            if(lookup_function_table_returntype.get(0).equals("")){
//                    System.out.println("Undeclared");
//                }
                        
             
            //lookup_variable_for_function_table_to_class_table = (String) lookup_function_table_returntype.get(0);
            if(insert_of_main_table_is_true==true && lookup_function_table_returntype.get(0).equals("")){
                
                //lookup_variable_for_function_table_to_class_table=name_of_class;
                //lookup_att_class_table
                List<Object> lookup_att_class_table_returntype=lookup_att_class_table(name_of_function_table,lookup_variable_for_function_table_to_class_table);
                if(lookup_att_class_table_returntype.get(0).equals(""))System.out.println("Undeclare");
                lookup_variable_for_function_table_to_class_table=(String)lookup_att_class_table_returntype.get(0);
            }
            
            if(insert_of_main_table_is_true==true && !lookup_function_table_returntype.get(0).equals("")){
                
                lookup_variable_for_function_table_to_class_table=(String)lookup_function_table_returntype.get(0);
                
            }
            
                        
            index++;
            if(arrayList.get(index).classPart.equals("Identifier")){
                name_of_function_table =arrayList.get(index).valuePart;
            
            index++;
            if(aj()){
                return true;
           
        }
        }
        }
                    else{return true;}
                    
                }
        }

        
        
        
        
        
        return false;
    }
    
    boolean xxp(){
        
        if(assignst1()){
           
            return true;
        }
        if(arrayList.get(index).classPart.equals("additive")){
            
//            List<Object> lookup_function_table_returntype=lookup_function_table(name_of_function_table,stack.peek());
//            if(lookup_function_table_returntype.get(0).equals("")){
//                    System.out.println("Undeclared");
//                }
////            stack = (Stack<Integer>) copy_of_stack.clone();
////                copy_of_stack.clear();

        
        // SAME CODE AS IN TERMINAL C()
        List<Object> lookup_function_table_returntype=lookup_function_table(name_of_function_table,stack.peek());
            if(insert_of_main_table_is_true==true && lookup_function_table_returntype.get(0).equals("")){
                
                
                List<Object> lookup_att_class_table_returntype=lookup_att_class_table(name_of_function_table,lookup_variable_for_function_table_to_class_table);
                if(lookup_att_class_table_returntype.get(0).equals(""))System.out.println("Undeclare");
                lookup_variable_for_function_table_to_class_table=(String)lookup_att_class_table_returntype.get(0);
            }
            
            if(insert_of_main_table_is_true==true && !lookup_function_table_returntype.get(0).equals("")){
                
                lookup_variable_for_function_table_to_class_table=(String)lookup_function_table_returntype.get(0);
                
            }

            index++;
            if(cc()){
            
            return true;
        }
        }
        if(arrayList.get(index).classPart.equals("multiplicative")){
            index++;
            if(tt()){
            
            return true;
        }
        }
        if(arrayList.get(index).valuePart.equals("*")){
            index++;
            if(tt()){
            
            return true;
        }
        }
        if(arrayList.get(index).valuePart.equals("/")){
            index++;
            if(tt()){
            
            return true;
        }
        }
        if(arrayList.get(index).valuePart.equals("%")){
            index++;
            if(tt()){
            
            return true;
        }
        }
        return false;
    }
    
    
    
    
    
    
    
    boolean xr(){
        if(arrayList.get(index).classPart.equals("(")){
            index++;
            if(pc()){
           
            if(arrayList.get(index).classPart.equals(")")){
            index++;
            if(cs()){
            
            return true;
        }
        }
        }
        }
        if(xxp()){
            
            return true;
        }
        return false;
    }
    
    boolean cs(){
        String a1[] = {"[",".",";","returnClass","breakContinueClass","doClass","ifClass","foreachClass","whileClass","tryClass","throwClass","additive","superClass","Identifier","datatype","thisClass"};
        for(int i=0; i<a1.length;i++){
                if(arrayList.get(index).classPart.equals(a1[i])){
                    if(ref1ref()){
            
            return true;
        }
                    else{return true;}
                }
        }
        
        
        
        
        
        return false;
    }
    
    boolean zp(){
        if(arrayList.get(index).classPart.equals("Identifier")){
            name_of_function_table = arrayList.get(index).valuePart;
            index++;
            if(ppp()){
            
            return true;
        }
        }
        return false;
    }
    
    
    
    boolean ppp(){
        if(declare()){
            
            return true;
        }
        if(array1()){
            
            return true;
        }
        return false;
    }
    
    boolean gz(){
        if(declare()){
            
            return true;
        }
        if(array()){
            
            return true;
        }
        return false;
    }
    
    
    
    void create_datatable(String name, String type, String access_modifier, String type_modifier, String link){
        
    }
    
    
    boolean insert_main_table(String name, String type, String access_modifier, String catagory, String parent){
        
         for(int i =0; i<main_table_arraylist.size(); i++){
             if(name.equals(main_table_arraylist.get(i).name)){
                 
                 System.out.println("Reclaration Error");
                 return false;
             }
         }
         
         main_table_arraylist.add( new main_table(name ,type ,access_modifier, catagory, parent )  );
//            name_of_class = "";
//            type_of_class = "";
//            access_modifier_of_class = "";
//            catagory_of_class = "";
//             this.parent = "";
         
         return true;
        
        
    }
    
    
    
    
    List<Object> lookup_main_table(String name) {
        for(int i =0; i<main_table_arraylist.size(); i++){
             if(name.equals(main_table_arraylist.get(i).name)){
                 
                 return Arrays.asList(main_table_arraylist.get(i).type,main_table_arraylist.get(i).category ,main_table_arraylist.get(i).parent,main_table_arraylist.get(i).access_modifier);
             }
         }
        
        return Arrays.asList("","" ,"","");
    }


    void create_scope(){
        stack.push(stack_number);
        stack_number++;
    }
    
    void destory_scope(){
        stack.pop();
    }
    
    
    
    
    
    boolean insert_class_table(String name, String type, String access_modifier, String type_modifier, String link){
        String a="";
        String b="";
        String variable_for_deleting_return_type_from_function = "";
        // for function checking 
        if(type.indexOf(",")!=-1){
            //StringBuilder stringBuilder = new StringBuilder(type);
             
                            for(int i =0; i<type.length(); i++){
                                //System.out.println(stringBuilder.charAt(i));
                                if(   ','==type.charAt(i)      ){
                                    //variable_for_deleting_return_type_from_function=variable_for_deleting_return_type_from_function+type.charAt(i);
                                    break;
                                }
                                variable_for_deleting_return_type_from_function=variable_for_deleting_return_type_from_function+type.charAt(i);
                                
                                
//                                stringBuilder.deleteCharAt(i);
                            }
//                            a=type;
//                            System.out.println(a);
//                            a.replaceFirst(variable_for_deleting_return_type_from_function, "");
                            for(int i =variable_for_deleting_return_type_from_function.length();i<type.length();i++){
                                a=a+type.charAt(i);
                            }
//                            System.out.println(a);
//                            System.out.println(variable_for_deleting_return_type_from_function);
                            
                           // type.replaceFirst(variable_for_deleting_return_type_from_function, "");
                            //System.out.println(type.replaceFirst(variable_for_deleting_return_type_from_function, ""));
                            
                            
                            for(int i =0; i<separate_class_table.size(); i++){
                                String supporting_for_above_variable="";
                                for(int j =0; j<separate_class_table.get(i).type.length(); j++){
                                    if(','==separate_class_table.get(i).type.charAt(j)){
                                        //supporting_for_above_variable=supporting_for_above_variable+separate_class_table.get(i).type.charAt(j);
                                        break;
                                    }
                                    supporting_for_above_variable=supporting_for_above_variable+separate_class_table.get(i).type.charAt(j);
                                }
                                
                                for(int q =supporting_for_above_variable.length();q<separate_class_table.get(i).type.length();q++){
                                    b=b+separate_class_table.get(i).type.charAt(q);
                                }
//                            System.out.println(a);
//                            System.out.println(variable_for_deleting_return_type_from_function);                                
//                                b=separate_class_table.get(i).type;
//                                b.replaceFirst(supporting_for_above_variable, "");
                                
                                
                                if(name.equals(separate_class_table.get(i).name) && a.equals(b)){
                                    
                                    System.out.println("Reclaration Error");
                                    return false;
                                }
                            }
                            
                            
                            
                            
                 }
        
        
        
        // for constructor and attribute checking
        
        if(type.indexOf(",")==-1 && type.indexOf("/")==-1){
            for(int i =0; i<separate_class_table.size(); i++){
             if(name.equals(separate_class_table.get(i).name) && type.equals(separate_class_table.get(i).type)){
                 
//                 if(separate_class_table.get(i).type.indexOf(",")!=-1){
//                     
//                 }
                 
                 
                 System.out.println("Reclaration Error");
                 return false;
             }
         }
            
            
        }
        
        if(type.indexOf("/")!=-1 && !name.equals(name_of_class)){
            System.out.println("should have valid constructor name");
            return false;
        }
        
        
        if(type.indexOf("/")!=-1 ){
            for(int i =0; i<separate_class_table.size(); i++){
             if( name.equals(separate_class_table.get(i).name) &&  type.equals(separate_class_table.get(i).type) ){
                 
//                 if(separate_class_table.get(i).type.indexOf(",")!=-1){
//                     
//                 }
                 
                 
                 System.out.println("Reclaration Error");
                 return false;
             }
         }
            
            
        }
        
        
        
        
//        if(type.indexOf("/")!=-1){
//            
//        }
//        
        
        
        
        
        
        
        
//        for(int i =0; i<separate_class_table.size(); i++){
//             if(name.equals(separate_class_table.get(i).name)){
//                 
////                 if(separate_class_table.get(i).type.indexOf(",")!=-1){
////                     
////                 }
//                 
//                 
//                 System.out.println("Reclaration Error");
//                 return false;
//             }
//         }
        name_of_data_table = "";
        type_of_data_table = "";
        type_modifier_of_data_table = "";
        
        access_modifier_of_datable = "";
        name_of_function_table = "";
        type_of_funtion_table = "";
        ///String access_modifier_of_class = "";
        if(!name.equals(""))separate_class_table.add( new class_table(name ,type ,access_modifier, type_modifier, link )  );
        return true;
        
  
    }
    
    boolean insert_function_table(String name, String type, int scope){
       
        for(int i =0; i<function_table.size(); i++){
             if(name.equals(function_table.get(i).name) && scope==function_table.get(i).scope){
                 
                 System.out.println("Reclaration Errorr");
                 return false;
             }
         }
        if(!name.equals(""))function_table.add( new function_table(name ,type ,scope)  );
        return true;
        
  
    }
    
    List<Object> lookup_function_table(String name, int scope){
        
        for(int j =0 ; j<stack.size()-1;j++){
            for(int i =0; i<function_table.size(); i++){
             if(name.equals(function_table.get(i).name) && scope==function_table.get(i).scope){
                 int a= stack.get(0);
                 stack.clear();
                 stack.push(a);
                 
                 return Arrays.asList(function_table.get(i).type);
             }
         }
            scope=stack.get(stack.size()-2-j);
            
            
        }
        int a= stack.get(0);
        stack.clear();
        stack.push(a);
        
        return Arrays.asList("");
        
        
        
          
        
        //parent
        
        
        //return Arrays.asList("");
    }
    
    
    
    
    List<Object> lookup_att_class_table(String name, String link){
           
       for(int i=0;i<store_all_class_table.size();i++){
             for(int j=0;j<store_all_class_table.get(i).separate_class_table.size();j++){
                              //System.out.println( store_all_class_table.get(i).separate_class_table.get(j).name+"  "+store_all_class_table.get(i).separate_class_table.get(j).type+"    "+ store_all_class_table.get(i).separate_class_table.get(j).access_modifier+"    "+ store_all_class_table.get(i).separate_class_table.get(j).type_modifier+"      "+ store_all_class_table.get(i).separate_class_table.get(j).link);
                              if(name.equals(store_all_class_table.get(i).separate_class_table.get(j).name) && link.equals(store_all_class_table.get(i).separate_class_table.get(j).link) && -1==store_all_class_table.get(i).separate_class_table.get(j).type.indexOf(",")){
                                  
                                                   return Arrays.asList(store_all_class_table.get(i).separate_class_table.get(j).type, store_all_class_table.get(i).separate_class_table.get(j).access_modifier, store_all_class_table.get(i).separate_class_table.get(j).type_modifier);

                              }
                              
             }
         }


            for(int i =0; i<separate_class_table.size(); i++){
             if(name.equals(separate_class_table.get(i).name) && link.equals(separate_class_table.get(i).link)){
                 
                 return Arrays.asList(separate_class_table.get(i).type,separate_class_table.get(i).access_modifier ,separate_class_table.get(i).type_modifier);
             }
            }
       return Arrays.asList("","" ,"");
    }
    
    
    List<Object> lookup_fn_class_table(String name,String pl, String link){
           for(int i=0;i<store_all_class_table.size();i++){
             for(int j=0;j<store_all_class_table.get(i).separate_class_table.size();j++){
                              //System.out.println( store_all_class_table.get(i).separate_class_table.get(j).name+"  "+store_all_class_table.get(i).separate_class_table.get(j).type+"    "+ store_all_class_table.get(i).separate_class_table.get(j).access_modifier+"    "+ store_all_class_table.get(i).separate_class_table.get(j).type_modifier+"      "+ store_all_class_table.get(i).separate_class_table.get(j).link);
                              if(name.equals(store_all_class_table.get(i).separate_class_table.get(j).name) && link.equals(store_all_class_table.get(i).separate_class_table.get(j).link) && -1!=store_all_class_table.get(i).separate_class_table.get(j).type.indexOf(",")){
                                  
                                                   return Arrays.asList(store_all_class_table.get(i).separate_class_table.get(j).type, store_all_class_table.get(i).separate_class_table.get(j).access_modifier, store_all_class_table.get(i).separate_class_table.get(j).type_modifier);

                              }
                              
             }
         }
       return Arrays.asList("","" ,"");
    }
    
    String compatibility(String lefttype, String righttype, String operator){
//        System.out.println(operator);
        if( ( operator.equals("*") ||  operator.equals("+") || operator.equals("*") || operator.equals("/")    )   && lefttype.equals("floatConstant") && righttype.equals("floatConstant")    ){
            //System.out.println(lefttype);
            return lefttype;
        }
        if( ( operator.equals("*") ||  operator.equals("+") || operator.equals("*") || operator.equals("/")    )   && lefttype.equals("floatConstant") && righttype.equals("float")    ){
            //System.out.println(lefttype);
            return righttype;
        }
        return "";
    }
          
          
    
    
}


    
    
    
    
    
    
    
  /*  public List<Object> floatConstant(char[] chh, int ii){
        int count= 0;
        int i = 0;
        String floatt = "";
        if(chh[ii]==46){
            
            for(i=ii;i<chh.length;i++){
                if(chh[i]>=48&&chh[i]<=57){
                    floatt = floatt + chh[i];
                }
                else if(count==0){
                    floatt = floatt + chh[i];
                    count = count +1;
                }
                else{
                    i=i-1;
                    return Arrays.asList(true, i , floatt);
                }
            }
            return Arrays.asList(false, i , floatt);
        }
        else if(chh[ii]>=48 && chh[ii]<=57){
            
            for(i=ii;i<chh.length;i++){
                if(chh[i]>=48 && chh[i]<=57){
                    floatt = floatt+chh[i];
                }
                else if(chh[i]==46){
                    floatt = floatt + chh[i];
                    count = count +1;
                }
                else{
                    i=i-1;
                    return Arrays.asList(true, i , floatt);
                }
            }
        }
        else{
            return Arrays.asList(true, i , floatt);
        }
        return Arrays.asList(true, i , floatt);
    }   */
    
    
    
    




class Tokenization{
    public String classPart;
    public String valuePart;
    public int LineNummber;
    Tokenization(String classPart, String valuePart, int LineNumber){
        this.classPart = classPart;
        this.valuePart = valuePart;
        this.LineNummber = LineNumber;
    }
    
}




class main_table{  
  String name;  
  String type;  
  String access_modifier;
  String category;
  String parent;
  

    public main_table(String name, String type, String access_modifier, String category, String parent) {
        this.name = name;
        this.type = type;
        this.access_modifier = access_modifier;
        this.category = category;
        this.parent = parent;
        
    }
  
  }  


class class_table{  
  String name;  
  String type;  
  String access_modifier;
  String type_modifier;
  String link;

    public class_table(String name, String type, String access_modifier, String type_modifier, String link) {
        this.name = name;
        this.type = type;
        this.access_modifier = access_modifier;
        this.type_modifier = type_modifier;
        this.link = link;
    }
  
  }  


class function_table{
    String name;  
  String type;  
  int scope;


    public function_table(String name, String type, int scope) {
        this.name = name;
        this.type = type;
        this.scope = scope;
      
    }
}
  

class store_all_class_table{
    ArrayList<class_table> separate_class_table;

    public store_all_class_table(ArrayList<class_table> separate_class_table) {
        this.separate_class_table = separate_class_table;
    }
}


