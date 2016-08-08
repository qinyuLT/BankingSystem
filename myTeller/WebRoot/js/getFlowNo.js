	var xmlHttp ;
		var flag = false ;
		function createXMLHttp(){
			if(window.XMLHttpRequest){
				xmlHttp = new XMLHttpRequest() ;
			} else {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP") ;
			}
		}
		function checkUserid(){
			createXMLHttp() ;
			xmlHttp.open("post","FlowNoServlet") ;
			xmlHttp.onreadystatechange = checkUseridCallback ;
			xmlHttp.send(null) ;
		}
		function checkUseridCallback(){
			if(xmlHttp.readyState == 4){
				if(xmlHttp.status == 200){
					
					var text = xmlHttp.responseText ;
					 if(text!=null){
					 document.getElementById("username").value=text;
					 document.getElementById("b1").disabled="disabled";
						flag = true ;
					}else{
						flag= false;
					}
				}
			}
		}