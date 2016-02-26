delete from BS_ADMENFORCE where aeno like '%成都征信%';
delete from BS_ADMPUNISH where aeno like '%成都征信%';
delete from BS_ADMPUNISHCONS where aeno like '%成都征信%';
delete from BS_AECONCLUSION where aeno like '%成都征信%';
delete from BS_AEINSPECTION where aeno like '%成都征信%';
delete from BS_AERECTIFICATION where aeno like '%成都征信%';

delete from BS_FACTBOOK where aeno like '%成都征信%';
delete from BS_WORKBASIS where filed8 like '%成都征信%';
delete from BS_WORKCOMING where filed8 like '%成都征信%';
delete from BS_WORKGOAWAY where filed15 like '%成都征信%';
delete from BS_WORKTALKSUMMARY where filed17 like '%成都征信%';
delete from BS_AEINSPECTION_ANL where aeno like '%成都征信%';

update BS_NOGENERATE set AENOINDEX  = 1,EVDCNOINDEX =1,FACTNOINDEX =1,AWAYNOINDEX=1,PBNSHNOINDEX=1,COMEININDEX=1 where orgno = 'A1000151000028' and AENOYEAR = '2015';