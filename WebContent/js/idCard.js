
var Wi = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1];
var ValideCode = [1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2];
function IdCardValidate1(idCard) {
	idCard = trim(idCard.replace(/ /g, ""));
	if (idCard.length == 15) {
		return isValidityBrithBy15IdCard(idCard);
	} else {
		if (idCard.length == 18) {
			var a_idCard = idCard.split("");
			if (isValidityBrithBy18IdCard(idCard) && isTrueValidateCodeBy18IdCard(a_idCard)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
/**  
 * 
 * @param a_idCard 
 * @return  
 */
function isTrueValidateCodeBy18IdCard(a_idCard) {
	var sum = 0;
	if (a_idCard[17].toLowerCase() == "x") {
		a_idCard[17] = 10;
	}
	for (var i = 0; i < 17; i += 1) {
		sum += Wi[i] * a_idCard[i];
	}
	var valCodePosition = sum % 11;
	if (a_idCard[17] == ValideCode[valCodePosition]) {
		return true;
	} else {
		return false;
	}
}
/**  
  * @param idCard 18
  * @return  
  */
function isValidityBrithBy18IdCard(idCard18) {
	var year = idCard18.substring(6, 10);
	var month = idCard18.substring(10, 12);
	var day = idCard18.substring(12, 14);
	var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
	if (temp_date.getFullYear() != parseFloat(year) || temp_date.getMonth() != parseFloat(month) - 1 || temp_date.getDate() != parseFloat(day)) {
		return false;
	} else {
		return true;
	}
}
/**  
   * @param idCard15 15
   * @return  
   */
function isValidityBrithBy15IdCard(idCard15) {
	var year = idCard15.substring(6, 8);
	var month = idCard15.substring(8, 10);
	var day = idCard15.substring(10, 12);
	var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
	if (temp_date.getYear() != parseFloat(year) || temp_date.getMonth() != parseFloat(month) - 1 || temp_date.getDate() != parseFloat(day)) {
		return false;
	} else {
		return true;
	}
}
function trim(str) {
	return str.replace(/(^\s*)|(\s*$)/g, "");
}
function IdCardValidateOfPassport(idCard) {
	var Expression = /(P\d{7})|(G\d{8})/;
	var objExp = new RegExp(Expression);
	if (objExp.test(idCard)) {
		return true;
	} else {
		return false;
	}
}
function IdCardValidate(num) {
	num = num.toUpperCase();
	if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num))) {
		return false;
	}
	var len, re;
	len = num.length;
	if (len == 15) {
		re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
		var arrSplit = num.match(re);
		var dtmBirth = new Date("19" + arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
		var bGoodDay;
		bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
		if (!bGoodDay) {
			return false;
		} else {
			var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
			var arrCh = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
			var nTemp = 0, i;
			num = num.substr(0, 6) + "19" + num.substr(6, num.length - 6);
			for (i = 0; i < 17; i++) {
				nTemp += num.substr(i, 1) * arrInt[i];
			}
			num += arrCh[nTemp % 11];
			return true;
		}
	}
	if (len == 18) {
		re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
		var arrSplit = num.match(re);
		var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
		var bGoodDay;
		bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
		if (!bGoodDay) {
			return false;
		} else {
			var valnum;
			var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
			var arrCh = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
			var nTemp = 0, i;
			for (i = 0; i < 17; i++) {
				nTemp += num.substr(i, 1) * arrInt[i];
			}
			valnum = arrCh[nTemp % 11];
			if (valnum != num.substr(17, 1)) {
				return false;
			}
			return true;
		}
	}
	return false;
}

