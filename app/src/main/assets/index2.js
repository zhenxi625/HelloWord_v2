var count = 0;
function wave(){
    if(count %2 == 0){
        document.getElementById("droid").src="ic_launcher.png";
    }else{
        document.getElementById("droid").src="icon.png";
    }
    count ++;
}

function show(jsondata){
     //jsondata接收一个字符串  [{name:"xxx",amount:600,phone:"13988888"},{name:"bb",amount:200,phone:"1398788"}]
     var jsonobjs = eval(jsondata);
     var table = document.getElementById("personTable");
     for(var y=0; y<jsonobjs.length; y++){
          var tr = table.insertRow(table.rows.length); //添加一行
          //添加三列，动态的往表格后面添加一行。
          var td1 = tr.insertCell(0);
          var td2 = tr.insertCell(1);
          td2.align = "center";
          var td3 = tr.insertCell(2);
          td3.align = "center";
          //设置列内容和属性
          td1.innerHTML = jsonobjs[y].name;
          td2.innerHTML = jsonobjs[y].amount;
          td3.innerHTML = "<a href='javascript:contact.call(\""+ jsonobjs[y].phone+ "\")'>"+ jsonobjs[y].phone+ "</a>";
     }
}