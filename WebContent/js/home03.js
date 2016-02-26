//Switch Tab Effect

function switchTab(tabpage,tabid){

        var oItem = document.getElementById(tabpage);   
		for(var i=0;i<oItem.children.length;i++){
		var x = oItem.children(i);  
		x.className = "";

        //var y = x.getElementsByTagName('a');

       // y[0].style.color="#1c4c88";

    }    

    document.getElementById(tabid).className = "Selected";

    var dvs=document.getElementById("cnt").getElementsByTagName("div");

    for (var i=0;i<dvs.length;i++){

      if (dvs[i].id==('d'+tabid))

        dvs[i].style.display='block';

      else

        dvs[i].style.display='none';

    }

}



// JavaScript Document