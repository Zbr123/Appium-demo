//package core.utils.TestRailConnector;
//
//import core.utils.ConfigUtil;
//import core.testrail.APIClient;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Properties;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class TestRailRun {
//    Properties myProp;
//    public Properties setConfig(){ return myProp = ConfigUtil.getConfig("config"); }
//
//    String url = setConfig().getProperty("testRailurl");
//    String username = setConfig().getProperty("username");
//    String password = setConfig().getProperty("password");
//    String projectName = setConfig().getProperty("projectName");
//    String testSuiteName = setConfig().getProperty("suiteName");
//    APIClient client = new APIClient(url);
//    TestRailData td = new TestRailData();
//    DateFormat timeFormat = new SimpleDateFormat("HHmmss");
//    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//    Calendar cal = Calendar.getInstance();
//    String AutomationTestRunName = "";
//    int AutomationTestRunId = 0;
//
//    String ProjectRootPath = System.getProperty("user.dir");
//    String ReportResouceDir = File.separator+"Reports"+File.separator+"cucumber-reports"+File.separator+"Cucumber.json";
//    File cucumberReportFile = new File(ProjectRootPath + ReportResouceDir);
//
//    public String createMilestone() throws IOException{
//
//        td.setTestRailConnections(client,username, password);
//        String date,time;
//        String mileStoneId = "";
//        date = dateFormat.format(cal.getTime());
//        time = timeFormat.format(cal.getTime());
//        String projectId = td.getProjectId(projectName);
//        try{
//            JSONObject obj = new JSONObject();
//            String strMile = testSuiteName.substring(0,10);
//            obj.put("name", strMile + "MileStone"+ date + time);
//            Object addedMileStoneObj =  client.sendPost("add_milestone/"+projectId,obj);
//            mileStoneId = ((JSONObject) addedMileStoneObj).get("id").toString();
//            String mileStoneName = ((JSONObject) addedMileStoneObj).get("name").toString();
//            System.out.println("MileStone created with Id: " +mileStoneId + " MileStone Name: " +mileStoneName);
//            if(!mileStoneId.equals("")){
//                return mileStoneId;
//            }else{
//                mileStoneId = "NoMileStoneId";
//                return mileStoneId;
//            }
//        }
//        catch (Exception ex){
//            System.out.println(ex);
//        }
//        return mileStoneId;
//    }
//
//    public void createTestRun()throws IOException{
//        try{
//            td.setTestRailConnections(client,username, password);
//            String date,time;
//            date = dateFormat.format(cal.getTime());
//            time = timeFormat.format(cal.getTime());
//            String projectId = td.getProjectId(projectName);
//            String suiteId = td.getTestSuiteId(testSuiteName,projectId);
//            String mileStoneId = createMilestone();
//            String testRunId = "";
//
//            if(!mileStoneId.equals("NoMileStoneId")){
//                JSONObject obj = new JSONObject();
//                obj.put("name", testSuiteName + "TestRun" + date + time);
//                obj.put("suite_id", Integer.parseInt(suiteId));
//                obj.put("milestone_id", Integer.parseInt(mileStoneId));
//                Object addedTestRunObj =  client.sendPost("add_run/"+projectId,obj);
//                testRunId = ((JSONObject) addedTestRunObj).get("id").toString();
//                String testRunName = ((JSONObject) addedTestRunObj).get("name").toString();
//                System.out.println("TestRun created with Id: "+testRunId+ " TestRun : "+testRunName);
//                AutomationTestRunName = testRunName;
//                AutomationTestRunId = Integer.parseInt(testRunId);
//            }else {
//                System.out.println("Please create milestone for the TestRun");
//            }
//        }
//        catch (Exception ex){
//            System.out.println(ex);
//        }
//    }
//
//    public void updateTestRunStatus()throws  IOException{
//        td.setTestRailConnections(client,username, password);
//
//        // This json parser is reading cucumber.json file and getting TestCaseID anf their Status
//        JSONParser parser = new JSONParser();
//        try{
//            Object obj = parser.parse(new FileReader(cucumberReportFile));
//            Object testCaseObj = new Object();
//            createTestRun();
//            int runID = AutomationTestRunId;
//            for(int i=0; i < ((JSONArray) obj).size(); i++){
//
//                ArrayList<String> testStatus = new ArrayList<String>();
//                testCaseObj = ((JSONObject) ((JSONArray) obj).get(i)).get("elements");
//                String testCaseName = ((JSONObject) ((JSONArray) testCaseObj).get(0)).get("name").toString();
//                int testCaseId = findTestIdFromScenarioName(((JSONObject) ((JSONArray) testCaseObj).get(0)).get("name").toString());
//
//                Object steps = ((JSONObject) ((JSONArray) testCaseObj).get(0)).get("steps"); // 5 object
//                for(int j=0; j < ((JSONArray) steps).size(); j++){
//                    Object results = ((JSONObject) ((JSONArray) steps).get(j)).get("result");
//                    String resultStatus = ((JSONObject) results).get("status").toString();
//                    testStatus.add(resultStatus);
//                }
//                String status = getTestStatus(testStatus);
//
//                // here we are updating status of the testcase from its run
//                updateTestRun(runID, testCaseId,status);
//            }
//        }
//        catch (Exception ex){
//            System.out.println(ex);
//        }
//    }
//
//    public int getStatuses(String statusName){
//
//        td.setTestRailConnections(client,username, password);
//        int statusId = 0;
//        try{
//            JSONArray c = (JSONArray) client.sendGet("get_statuses");
//            for ( int i = 0 ; i < c.size() ; i++ )
//            {
//                if(statusName.toLowerCase().equals(((JSONObject) ((JSONArray) c).get(i)).get("name").toString())){
//                    statusId = Integer.parseInt(((JSONObject) ((JSONArray) c).get(i)).get("id").toString());
//                }
//            }
//        }
//        catch(Exception ex){
//            System.out.println(ex);
//        }
//        return statusId;
//    }
//
//    public int findTestIdFromScenarioName(String line){
//        Pattern p = Pattern.compile("\\[(.*?)\\]");
//        Matcher m = p.matcher(line);
//        while (m.find()) {
//            line = m.group().replace("[","");
//            line = line.replace("]","");
//        }
//        return Integer.parseInt(line);
//    }
//
//    public String getTestStatus(ArrayList<String> resultStatus){
//
//        int count = 0;
//        String status = "";
//        if(resultStatus.contains("undefined")){
//            status = "blocked";
//            return status;
//        }else if(resultStatus.contains("failed") || resultStatus.contains("skipped")){
//            //return "failed";
//            status = "failed";
//            return status;
//        }else{
//            for(int i=0; i < resultStatus.size(); i++){
//                if(resultStatus.contains("passed")){
//                    count++;
//                }
//            }
//            if(count==resultStatus.size()){
//                status = "passed";
//            }
//            return status;
//        }
//    }
//
//    public void updateTestRun(int runID, int caseId, String statusName){
//
//        int statusId = getStatuses(statusName);
//        int testCaseId = caseId;
//        String comment = "Test Comments";
//
//        try{
//            JSONObject testCaseObject = new JSONObject();
//            JSONArray resultsArray = new JSONArray();
//            JSONObject allObject = new JSONObject();
//            testCaseObject.put("case_id", testCaseId);
//            testCaseObject.put("status_id", statusId);
//            testCaseObject.put("comment", comment);
//
//            resultsArray.add(testCaseObject);
//            allObject.put("results",resultsArray);
//
//            // this api call gets test case id of the test case
//            Object testRunStatus =  client.sendPost("add_results_for_cases/"+runID,allObject);
//            System.out.println("TestCase: "+testCaseId+" status updated as: "+statusName+" in TestRun: "+runID);
//        }
//        catch(Exception ex){
//            System.out.println(ex);
//        }
//    }
//
//    // This is supportive method used to delete milestones from TestRail
//    public void deleteMilestone(String mileStoneId){
//        td.setTestRailConnections(client,username, password);
//        try{
//            JSONObject obj = new JSONObject();
//            Object deletedMileStoneObj = client.sendPost("delete_milestone/"+ mileStoneId,obj);
//            if(deletedMileStoneObj.toString().equals("{}")){
//                System.out.println("MileStone with ID: " + mileStoneId + " deleted successfully");
//            }
//        }
//        catch (Exception ex){
//
//            System.out.println(ex);
//        }
//    }
//
//    // This is supportive method used to delete TestRun from TestRail
//    public void deleteTestRun(int testRunId) {
//        td.setTestRailConnections(client,username, password);
//        try{
//            JSONObject obj = new JSONObject();
//            Object deletedRunObj = client.sendPost("delete_run/"+ testRunId,obj);
//            if(deletedRunObj.toString().equals("{}")){
//                System.out.println("TestRun with ID: " + testRunId + " deleted successfully");
//            }
//        }
//        catch (Exception ex){
//
//            System.out.println(ex);
//        }
//    }
//}
