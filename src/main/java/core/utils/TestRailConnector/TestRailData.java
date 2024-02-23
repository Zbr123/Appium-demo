//package core.utils.TestRailConnector;
//
//import core.utils.ConfigUtil;
//import core.testrail.APIClient;
//import org.apache.commons.io.filefilter.DirectoryFileFilter;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Properties;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class TestRailData {
//
//    Properties myProp;
//    public Properties setConfig(){ return myProp = ConfigUtil.getConfig("config"); }
//
//    String url = setConfig().getProperty("testRailurl");
//    String username = setConfig().getProperty("username");
//    String password = setConfig().getProperty("password");
//    String projectName = setConfig().getProperty("projectName");
//    String testSuiteName = setConfig().getProperty("suiteName");
//    APIClient client = new APIClient(url);
//    // This is the area in TestRail where we are getting values and putting them in a file
//    String testCaseNameArea = "title";
//    String testCaseContentArea = "custom_bdd_gherkin";
//    String testCaseId = "id";
//
//    String ProjectRootPath = System.getProperty("user.dir");
//    String PathToResourcesDir = File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"Features"+File.separator+"Foree"+File.separator;
//    String PathToJavaDir = File.separator+"src"+File.separator+"test"+File.separator+"java"+File.separator+"com"+File.separator+"Foree"+File.separator;
//
//    public void createFeatureFiles()throws FileNotFoundException{
//
//        try{
//            //APIClient client = new APIClient(url);
//            System.out.println("Creating .feature Files for TestCases in TestRail > AutomationTestSuite");
//            System.out.println("=======================================================================");
//            setTestRailConnections(client,username, password);
//            // Project related Ids that will be used by the API
//            String projectId = getProjectId(projectName);
//            String suiteId = getTestSuiteId(testSuiteName,projectId);
//
//            String getTestCaseURL = getURL(projectId,suiteId);
//            JSONArray c = (JSONArray) client.sendGet(getTestCaseURL);
//            //Here we are deleting all feature files
////           To get All Test Cases of a Project
//            for ( int i = 0 ; i < c.size() ; i++ )
//            {
//                if(((JSONObject) ((JSONArray) c).get(i)).get(testCaseContentArea) != null){
//                    //System.out.println("Test Content is NOT blank");
//                    //System.out.println(c.toJSONString());
//                    String featureName = ((JSONObject) ((JSONArray) c).get(i)).get(testCaseNameArea).toString().replace(' ', '_');
//                    String featureContents = ((JSONObject) ((JSONArray) c).get(i)).get(testCaseContentArea).toString();
//                    String featureid = ((JSONObject) ((JSONArray) c).get(i)).get(testCaseId).toString();
//                    //System.out.println(featureid);
//                    featureContents = replaceGherkinBracket(featureContents);
//                    featureContents=addID(featureid,featureContents);
//                    String sectionName = getSectionName(projectId, suiteId, Integer.parseInt((((JSONObject) ((JSONArray) c).get(i)).get("section_id")).toString()));
//                    String correctedFeatureName = removeSpecialChars(featureName);
//                    sectionName = removeSpecialChars(sectionName);
//                    String testReourcePath = getResourcePath(sectionName);
//                    PrintWriter writer = new PrintWriter(testReourcePath + correctedFeatureName + ".feature");
//                    writer.println(featureContents);
//                    writer.close();
//                    System.out.println("Feature file of Feature: "+featureName+" created successfully");
//                }
//            }
//            createFeatureTestFile(projectName);
//        }
//        catch (Exception ex){
//            System.out.println(ex);
//        }
//    }
//
//    String addID(String featureid, String sentence){
//        String search  = "Scenario:";
//        String searchGiven  = "Given ";
//        String searchWhen  = "When ";
//        String searchThen  = "Then ";
//        String searchAnd  = "And ";
//
//        String result="";
//        String lines[] = sentence.split("\\r?\\n");
//        for(int i = 0; i < lines.length;i++) {
////            System.out.println(lines[i]);
//            if (lines[i].indexOf(search) != -1) {
//
//                //       System.out.println("I found the keyword");
//                lines[i] = lines[i] + " - TestCaseId["+featureid+"]";
//                //       System.out.println(lines[i]);
//
//            }
////            else if (lines[i].indexOf(searchGiven) != -1) {
////
////                //       System.out.println("I found the keyword");
////                lines[i] = lines[i] + "_"+featureid;
////                //       System.out.println(lines[i]);
////
////            }
////            else if (lines[i].indexOf(searchWhen) != -1) {
////
////                //       System.out.println("I found the keyword");
////                lines[i] = lines[i] + "_"+featureid;
////                //       System.out.println(lines[i]);
////
////            }
////            else if (lines[i].indexOf(searchThen) != -1) {
////
////                //       System.out.println("I found the keyword");
////                lines[i] = lines[i] + "_"+featureid;
////                //       System.out.println(lines[i]);
////
////            }
////            else if (lines[i].indexOf(searchAnd) != -1) {
////
////                //       System.out.println("I found the keyword");
////                lines[i] = lines[i] + "_"+featureid;
////                //       System.out.println(lines[i]);
////
////            }
//        }
//        for (int i = 0; i<lines.length-1;i++) {
//            result = result+lines[i] +"\n";
//        }
//        result = result+lines[lines.length-1];
//
//        return result ;
//    }
//
//
//
//    public static void setTestRailConnections(APIClient client,String username, String pw ){
//        client.setUser(username);
//        client.setPassword(pw);
//    }
//
//    public String getResourcePath(String moduleName) throws  IOException{
//        // getting path of resource folder
//        String testReourcePath = "";
//        File moduleDir = new File(ProjectRootPath + PathToResourcesDir+moduleName);
//        if(moduleDir.exists()){
//            testReourcePath = ProjectRootPath +PathToResourcesDir+moduleName+File.separator;
//            return testReourcePath;
//        }
//        else{
//            try {
//                moduleDir.mkdir();
//                testReourcePath = ProjectRootPath + PathToResourcesDir+moduleName+File.separator;
//                return testReourcePath;
//            }catch (SecurityException se){
//                System.out.println(se);
//                return se.toString();
//            }
//        }
//    }
//
//    public String getTestFilePath(String projectName, String moduleName) throws  IOException{
//        // getting path of resource folder
//        File moduleTestDir = new File(ProjectRootPath + PathToJavaDir+moduleName);
//        try {
//            moduleTestDir.mkdir();
//            String testReourcePath = ProjectRootPath + PathToJavaDir+moduleName+File.separator;
//            return testReourcePath;
//        }catch (SecurityException se){
//            System.out.println(se);
//            return se.toString();
//        }
//    }
//
//    public static String removeSpecialChars(String featureName){
//        String featureNameFinal = "";
//        Pattern pt = Pattern.compile("[$&+,:;=?@#|'<>.^*()%!\\-\"/]");
//        Matcher match= pt.matcher(featureName);
//        while(match.find())
//        {
//            String s= match.group();
//            featureName=featureName.replaceAll("\\"+s, "_");
//        }
//        if(featureName.length() > 164){
//            featureNameFinal = featureName.substring(0,160);
//            return featureNameFinal;
//        }
//        else{
//            return featureName;
//        }
//    }
//
//    public static String replaceGherkinBracket(String featureContents){
//        String GherkinFinal = "";
//        Pattern pt = Pattern.compile("[()]");
//        Matcher match= pt.matcher(featureContents);
//        while(match.find())
//        {
//            String s= match.group();
//           featureContents=featureContents.replaceAll("\\"+s, "");
//        }
//
//        return featureContents;
//    }
//
//
//    public String getProjectId(String projectName){
//        setTestRailConnections(client,username, password);
//        String projectId = "";
//        try{
//            JSONArray c = (JSONArray) client.sendGet("get_projects");
//            for ( int i = 0 ; i < c.size() ; i++ )
//            {
//                if((((JSONObject) ((JSONArray) c).get(i)).get("name").toString().toLowerCase()).equals(projectName.toLowerCase()) )
//                {
//                    projectId = ((JSONObject) ((JSONArray) c).get(i)).get("id").toString();
//                    return  projectId;
//                }
//                else{
//                    projectId = "NoID";
//                }
//            }
//        }
//        catch(Exception ex){
//            System.out.println(ex);
//        }
//        return  projectId;
//
//    }
//
//    public String getTestSuiteId(String suiteName, String projectId){
//
//        setTestRailConnections(client,username, password);
//        String suiteId = "";
//        try{
//            JSONArray c = (JSONArray) client.sendGet("get_suites/"+projectId);
//            for ( int i = 0 ; i < c.size() ; i++ )
//            {
//                if((((JSONObject) ((JSONArray) c).get(i)).get("name").toString().toLowerCase()).equals(suiteName.toLowerCase()) )
//                {
//                    suiteId = ((JSONObject) ((JSONArray) c).get(i)).get("id").toString();
//                    return  suiteId;
//                }
//                else{
//                    suiteId = "NoID";
//                }
//            }
//        }
//        catch(Exception ex){
//            System.out.println(ex);
//        }
//        return suiteId;
//    }
//
//
//
//    public String getURL (String pID,String sID){
//        String url = "";
//        url = "get_cases/"+pID+"&suite_id="+sID;
//        return url;
//    }
//
//    public void createFeatureTestFile(String pName) throws  IOException{
//
//        System.out.println("\nCreating Template File for the created features");
//        System.out.println("===============================================");
//        ArrayList<String> sectionNames = new ArrayList<>();
//        File directory = new File(ProjectRootPath + PathToResourcesDir);
//        File[] subdirs = directory.listFiles((FileFilter) DirectoryFileFilter.DIRECTORY);
//        boolean check = false;
//        for (File dir : subdirs) {
//            sectionNames.add(dir.getName());
//        }
//        for(String sName:sectionNames){ // get one section from arraylist
////          step defs
//            File[] featurePaths;
//            File testDir = new File(ProjectRootPath + PathToJavaDir+sName);
//            String testDirPath = ProjectRootPath + PathToJavaDir+sName+File.separator;
//            if(testDir.exists()){
//                //System.out.println("Directory already created");
//                File f = new File(ProjectRootPath + PathToResourcesDir+sName+File.separator);
//                featurePaths = f.listFiles();
//                for(File featureFilePath:featurePaths) {
//                    String fileName = featureFilePath.getName();
//                    fileName = fileName.substring(0,fileName.lastIndexOf("."));
//
//                    if(new File(testDirPath + fileName + "Test.java").exists()){
//
//                        System.out.println("Feature Tests already created: "+fileName + "Test.java");
//                    }
//                    else{
//                        readContent(featureFilePath,testDirPath, sName);
//                    }
//                }
//            }
//            else{
//                String testFilePath = getTestFilePath(pName,sName);
//                File f = new File(ProjectRootPath + PathToResourcesDir+sName+File.separator);
//                featurePaths = f.listFiles();
//                for(File featureFilePath:featurePaths) {
//                    readContent(featureFilePath,testDirPath, sName);
//                }
//            }
//        }
//    }
//
//    public void readContent(File file, String testDirPath, String sectionName) throws IOException{
//
//        String fName = file.getName();
//        fName = fName.substring(0,fName.lastIndexOf(".")); //Is_it_Friday_yetTest.java
//        String sName = sectionName;
//
//        try(BufferedReader br  = new BufferedReader(new FileReader(file))){
//            String strLine;
//
//            String fixedContent = "/* package com.Foree."+sName+";\n" +
//                    "\n" +
//                    "import com.Foree.utils.BrowerConfig.WebConnector;\n" +
//                    "import cucumber.api.java.en.And;\n" +
//                    "import cucumber.api.java.en.Given;\n" +
//                    "import cucumber.api.java.en.Then;\n" +
//                    "import cucumber.api.java.en.When;\n" +
//                    "import org.openqa.selenium.WebDriver;\n" +
//                    "import sun.security.util.PendingException;\n" +
//                    "\n" +
//                    "public class "+fName+"Test {\n" +
//                    "\n" +
//                    "    WebConnector connector = new WebConnector();\n" +
//                    "    WebDriver driver  = connector.openBrowser();\n" +
//                    "\n";
//            String givenContent = "";
//            String whenContent = "";
//            String thenContent = "";
//            String andContent = "";
//            String endContent = "    \n" +
//                    "} */";
//            String givenMethodName = "";
//            String whenMethodName = "";
//            String thenMethodName = "";
//            String andMethodName = "";
//            String givenHeader = "";
//            String whenHeader = "";
//            String thenHeader = "";
//            String andHeader = "";
//
//
//            // Here we are writing in java file to create template
//            // creating java file
//            PrintWriter writer = new PrintWriter(testDirPath + fName + "Test.java");
//            // writing fixed Contents
//            writer.println(fixedContent);
//
//
//
//
//
//            // Read lines from the file, returns null when end of stream
//            // is reached
//            while((strLine = br.readLine()) != null){
//                strLine = strLine.trim();
//                if(!strLine.equals("")){
//                    if(strLine.substring(0,5).equals("Given")) //Given
//                    {
//                        givenContent = strLine.substring(5);
//                        givenMethodName = removeSpecialCharAddUnderScore(strLine.substring(5));
//                        givenHeader = replaceQuotedStringWithRegEx(givenContent);
//
//                        // This is given template
//                        writer.println("    @Given(\"^"+givenHeader+"\")");
//                        writer.println("    public void "+givenMethodName+"(String arg1, String arg2) throws Throwable {\n");
//                        writer.println("        // Write code here that turns the phrase above into concrete actions\n");
//                        writer.println("     throw new PendingException();\n" +
//                                "    }\n" +
//                                "    ");
//                    }
//                    if(strLine.substring(0,4).equals("When")) //When
//                    {
//                        whenContent = strLine.substring(4);
//                        whenMethodName = removeSpecialCharAddUnderScore(strLine.substring(4));
//                        whenHeader = replaceQuotedStringWithRegEx(whenContent);
//
//                        // This is when template
//                        writer.println("    @When(\"^"+whenHeader+"\")");
//                        writer.println("    public void "+whenMethodName+"(String arg1, String arg2) throws Throwable {\n");
//                        writer.println("        // Write code here that turns the phrase above into concrete actions\n");
//                        writer.println("     throw new PendingException();\n" +
//                                "    }\n" +
//                                "    ");
//
//                    }
//                    if(strLine.substring(0,4).equals("Then")) //Then
//                    {
//                        thenContent = strLine.substring(4);
//                        thenMethodName = removeSpecialCharAddUnderScore(strLine.substring(4));
//                        thenHeader = replaceQuotedStringWithRegEx(thenContent);
//
//                        // This is then template
//                        writer.println("    @Then(\"^"+thenHeader+"\")");
//                        writer.println("    public void "+thenMethodName+"(String arg1, String arg2) throws Throwable {\n");
//                        writer.println("        // Write code here that turns the phrase above into concrete actions\n");
//                        writer.println("     throw new PendingException();\n" +
//                                "    }\n" +
//                                "    ");
//
//
//                    }
//                    if(strLine.substring(0,3).equals("And")) //And
//                    {
//                        andContent = strLine.substring(3);
//                        andMethodName = removeSpecialCharAddUnderScore(strLine.substring(3));
//                        andHeader = replaceQuotedStringWithRegEx(andContent);
//
//                        if(!andHeader.equals("")){
//                            // This is and template
//                            writer.println("    @Given(\"^"+andHeader+"\")");
//                            writer.println("    public void "+andMethodName+"(String arg1, String arg2) throws Throwable {\n");
//                            writer.println("        // Write code here that turns the phrase above into concrete actions\n");
//                            writer.println("     throw new PendingException();\n" +
//                                    "    }\n" +
//                                    "    ");
//                        }
//                    }
//
//                }
//            }
//
////            // Here we are writing in java file to create template
////            // creating java file
////            PrintWriter writer = new PrintWriter(testDirPath + fName + "Test.java");
////            // writing fixed Contents
////            writer.println(fixedContent);
//
////            // This is given template
////            writer.println("    @Given(\"^"+givenHeader+"\")");
////            writer.println("    public void "+givenMethodName+"(String arg1, String arg2) throws Throwable {\n");
////            writer.println("        // Write code here that turns the phrase above into concrete actions\n");
////            writer.println("     throw new PendingException();\n" +
////                    "    }\n" +
////                    "    ");
////
////            // This is when template
////            writer.println("    @When(\"^"+whenHeader+"\")");
////            writer.println("    public void "+whenMethodName+"(String arg1, String arg2) throws Throwable {\n");
////            writer.println("        // Write code here that turns the phrase above into concrete actions\n");
////            writer.println("     throw new PendingException();\n" +
////                    "    }\n" +
////                    "    ");
////
////            // This is then template
////            writer.println("    @Then(\"^"+thenHeader+"\")");
////            writer.println("    public void "+thenMethodName+"(String arg1, String arg2) throws Throwable {\n");
////            writer.println("        // Write code here that turns the phrase above into concrete actions\n");
////            writer.println("     throw new PendingException();\n" +
////                    "    }\n" +
////                    "    ");
////
////            if(!andHeader.equals("")){
////                // This is and template
////                writer.println("    @Given(\"^"+andHeader+"\")");
////                writer.println("    public void "+andMethodName+"(String arg1, String arg2) throws Throwable {\n");
////                writer.println("        // Write code here that turns the phrase above into concrete actions\n");
////                writer.println("     throw new PendingException();\n" +
////                        "    }\n" +
////                        "    ");
////            }
//            // Writing end Contents
//            writer.println(endContent);
//            writer.close();
//            System.out.println("Feature Tests created successfully: "+fName);
//        }
//    }
//
//    public String replaceQuotedStringWithRegEx(String line){
//        line = line.trim();
//        Pattern p = Pattern.compile("\"([^\"]*)\"");
//        Matcher m = p.matcher(line);
//        while (m.find()) {
//            line = line.replaceAll(m.group(),"\\\\\"([^\\\\\"]*)\\\\\"");
////            line =line +"$";
//        }
//        line =line +"$";
//        return line;
//    }
//    public String removeSpecialCharAddUnderScore(String line){
//
//        String lineWithoutSpecialChar = line.replaceAll("\\\".*?\\\"", "");
//        String removeAllSpecial = lineWithoutSpecialChar.replaceAll("[\\-\\+\\.\\^:,']","").trim();
//        String lineWithUnderScore = removeAllSpecial.replace(' ', '_');
//        return lineWithUnderScore;
//    }
//
//    public String getSectionName(String projectId, String suiteId, int secId){
//
//        String sectionName = "";
//        try{
//            JSONArray c = (JSONArray) client.sendGet("get_sections/"+projectId+"&suite_id="+suiteId);
//            for ( int i = 0 ; i < c.size() ; i++ )
//            {
//                if(Integer.parseInt((((JSONObject) ((JSONArray) c).get(i)).get("id")).toString()) == secId)
//                {
//                    if(((JSONObject) ((JSONArray) c).get(i)).get("name").toString().length() > 164){
//                        String s1 = ((JSONObject) ((JSONArray) c).get(i)).get("name").toString();
//
//                        sectionName = s1.substring(0,164);
//                        sectionName = sectionName.replace(' ', '_');
//                        return  sectionName;
//                    }
//                    else{
//                        sectionName =  ((JSONObject) ((JSONArray) c).get(i)).get("name").toString();
//                        sectionName = sectionName.replace(' ', '_');
//                        return  sectionName;
//                    }
//                }
//                else{
//                    sectionName = "NoSectionName";
//                }
//            }
//        }
//        catch(Exception ex){
//            System.out.println(ex);
//        }
//        return sectionName;
//    }
//
//}
