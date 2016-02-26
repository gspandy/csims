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
	menuID.style.background="URL(/csims/images/zhenxin15.jpg) no-repeat";

  }
  else {
    odiv[num].style.display="none";
	menuID.style.background="URL(/csims/images/zhenxin14.jpg) no-repeat";
  }
  }
  
function change(menuID)
{
    menuID.style.background="URL(/csims/images/zhenxin14.jpg) no-repeat";
}