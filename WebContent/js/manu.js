function changbg(menuID,url)
{
	menuID.style.background="URL("+url+")";
}

function hideAll() {
  for(i=0;i<odiv.length;i++) {
     odiv[i].style.display="none";
  }
}

function showObj(menuID,num) {
  
  if (odiv[num].style.display=="none") {
    hideAll();
    odiv[num].style.display="inline";
	 menuID.style.background="URL(../images/left_bt1.jpg)";

  }
  else {
    odiv[num].style.display="none";
	 menuID.style.background="URL(../images/left_bt2.jpg)";
  }
  }
  
function change(menuID)
{
    menuID.style.background="URL(../images/left_bt2.jpg)";
}