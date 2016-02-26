--BS_ADMENFORCE
CREATE INDEX
    idx_AENO_aef
ON
    BS_ADMENFORCE
    (
        AENO
    );
CREATE INDEX
    idx_AEORGNO_aef
ON
    BS_ADMENFORCE
    (
        AEORGNO
    );
--BS_AEINSPECTION
CREATE INDEX
    idx_AENO_insp
ON
    BS_AEINSPECTION
    (
        AENO
    );
CREATE INDEX
    idx_INO_insp
ON
    BS_AEINSPECTION
    (
        INO
    );
CREATE INDEX
    idx_AEORGNO_insp
ON
    BS_AEINSPECTION
    (
        AEORGNO
    );
CREATE INDEX
    idx_AEEDORGNO_insp
ON
    BS_AEINSPECTION
    (
        AEEDORGNO
    );
CREATE INDEX
    idx_AEEDORGZZNO_insp
ON
    BS_AEINSPECTION
    (
        AEEDORGZZNO
    );
CREATE INDEX
    idx_AEEDORGXZHNO_insp
ON
    BS_AEINSPECTION
    (
        AEEDORGXZHNO
    );
CREATE INDEX
    idx_stat_insp
ON
    BS_AEINSPECTION
    (
        STAT
    );
--BS_AECONCLUSION
CREATE INDEX
    idx_AENO_conl
ON
    BS_AECONCLUSION
    (
        AENO
    );
CREATE INDEX
    idx_AEORGNO_conl
ON
    BS_AECONCLUSION
    (
        AEORGNO
    );
CREATE INDEX
    idx_AEEDORGNO_conl
ON
    BS_AECONCLUSION
    (
        AEEDORGNO
    );
CREATE INDEX
    idx_AEEDORGZZNO_conl
ON
    BS_AECONCLUSION
    (
        AEEDORGZZNO
    );
CREATE INDEX
    idx_AEEDORGXZHNO_conl
ON
    BS_AECONCLUSION
    (
        AEEDORGXZHNO
    );
--BS_AERECTIFICATION
CREATE INDEX
    idx_AENO_refti
ON
    BS_AERECTIFICATION
    (
        AENO
    );
CREATE INDEX
    idx_AEORGNO_refti
ON
    BS_AERECTIFICATION
    (
        AEORGNO
    );
CREATE INDEX
    idx_AEEDORGNO_refti
ON
    BS_AERECTIFICATION
    (
        AEEDORGNO
    );
CREATE INDEX
    idx_AEEDORGZZNO_refti
ON
    BS_AERECTIFICATION
    (
        AEEDORGZZNO
    );
CREATE INDEX
    idx_AEEDORGXZHNO_refti
ON
    BS_AERECTIFICATION
    (
        AEEDORGXZHNO
    );
--BS_ADMPUNISH
CREATE INDEX
    idx_AENO_punsh
ON
    BS_ADMPUNISH
    (
        AENO
    );
CREATE INDEX
    idx_PUNISHNO_punsh
ON
    BS_ADMPUNISH
    (
        PUNISHNO
    );
CREATE INDEX
    idx_AEORGNO_punsh
ON
    BS_ADMPUNISH
    (
        AEORGNO
    );
CREATE INDEX
    idx_AEEDORGNO_punsh
ON
    BS_ADMPUNISH
    (
        AEEDORGNO
    );
CREATE INDEX
    idx_AEEDORGZZNO_punsh
ON
    BS_ADMPUNISH
    (
        AEEDORGZZNO
    );
CREATE INDEX
    idx_AEEDORGXZHNO_punsh
ON
    BS_ADMPUNISH
    (
        AEEDORGXZHNO
    );
--BS_ADMPUNISHCONS
CREATE INDEX
    idx_AENO_punshcon
ON
    BS_ADMPUNISHCONS
    (
        AENO
    );
CREATE INDEX
    idx_PUNISHNO_punshcon
ON
    BS_ADMPUNISHCONS
    (
        PUNISHNO
    );
CREATE INDEX
    idx_AEORGNO_punshcon
ON
    BS_ADMPUNISHCONS
    (
        AEORGNO
    );
CREATE INDEX
    idx_AEEDORGNO_punshcon
ON
    BS_ADMPUNISHCONS
    (
        AEEDORGNO
    );
CREATE INDEX
    idx_AEEDORGZZNO_punshcon
ON
    BS_ADMPUNISHCONS
    (
        AEEDORGZZNO
    );
CREATE INDEX
    idx_AEEDORGXZHNO_punshcon
ON
    BS_ADMPUNISHCONS
    (
        AEEDORGXZHNO
    );
--BS_WORKBASIS
CREATE INDEX
    idx_STAT_wkbasis
ON
    BS_WORKBASIS
    (
        STAT
    );
CREATE INDEX
    idx_AEORGNO_wkbasis
ON
    BS_WORKBASIS
    (
        AEORGNO
    );
CREATE INDEX
    idx_AEEDORGNO_wkbasis
ON
    BS_WORKBASIS
    (
        AEEDORGNO
    );
CREATE INDEX
    idx_AENO_wkbasis
ON
    BS_WORKBASIS
    (
        FILED8
    );
CREATE INDEX
    idx_INO_wkbasis
ON
    BS_WORKBASIS
    (
        FILED7
    );
--BS_WORKCOMING
CREATE INDEX
    idx_STAT_wkcomi
ON
    BS_WORKCOMING
    (
        STAT
    );
CREATE INDEX
    idx_AEORGNO_wkcomi
ON
    BS_WORKCOMING
    (
        AEORGNO
    );
CREATE INDEX
    idx_AEEDORGNO_wkcomi
ON
    BS_WORKCOMING
    (
        AEEDORGNO
    );
CREATE INDEX
    idx_AENO_wkcomi
ON
    BS_WORKCOMING
    (
        FILED8
    );
CREATE INDEX
    idx_INO_wkcomi
ON
    BS_WORKCOMING
    (
        FILED7
    );
--BS_WORKGOAWAY
CREATE INDEX
    idx_STAT_wkgoaw
ON
    BS_WORKGOAWAY
    (
        STAT
    );
CREATE INDEX
    idx_AEORGNO_wkgoaw
ON
    BS_WORKGOAWAY
    (
        AEORGNO
    );
CREATE INDEX
    idx_AEEDORGNO_wkgoaw
ON
    BS_WORKGOAWAY
    (
        AEEDORGNO
    );
CREATE INDEX
    idx_AENO_wkgoaw
ON
    BS_WORKGOAWAY
    (
        FILED15
    );
CREATE INDEX
    idx_INO_wkgoaw
ON
    BS_WORKGOAWAY
    (
        FILED14
    );
--BS_WORKTALKSUMMARY
CREATE INDEX
    idx_STAT_wktksmry
ON
    BS_WORKTALKSUMMARY
    (
        STAT
    );
CREATE INDEX
    idx_AEORGNO_wktksmry
ON
    BS_WORKTALKSUMMARY
    (
        AEORGNO
    );
CREATE INDEX
    idx_AEEDORGNO_wktksmry
ON
    BS_WORKTALKSUMMARY
    (
        AEEDORGNO
    );
CREATE INDEX
    idx_AENO_wktksmry
ON
    BS_WORKTALKSUMMARY
    (
        FILED17
    );
CREATE INDEX
    idx_INO_wktksmry
ON
    BS_WORKTALKSUMMARY
    (
        FILED16
    );
--BS_FACTBOOK
CREATE INDEX
    idx_STAT_fcbk
ON
    BS_FACTBOOK
    (
        STAT
    );
CREATE INDEX
    idx_AEORGNO_fcbk
ON
    BS_FACTBOOK
    (
        AEORGNO
    );
CREATE INDEX
    idx_AEEDORGNO_fcbk
ON
    BS_FACTBOOK
    (
        AEEDORGNO
    );
CREATE INDEX
    idx_AENO_fcbk
ON
    BS_FACTBOOK
    (
        AENO
    );
CREATE INDEX
    idx_INO_fcbk
ON
    BS_FACTBOOK
    (
        FILED11
    );
--BS_AEINSPECTION_ANL
CREATE INDEX
    idx_STAT_inspanl
ON
    BS_AEINSPECTION_ANL
    (
        STAT
    );
CREATE INDEX
    idx_AEORGNO_inspanl
ON
    BS_AEINSPECTION_ANL
    (
        AEORGNO
    );
CREATE INDEX
    idx_AEEDORGNO_inspanl
ON
    BS_AEINSPECTION_ANL
    (
        AEEDORGNO
    );
CREATE INDEX
    idx_AENO_inspanl
ON
    BS_AEINSPECTION_ANL
    (
        AENO
    );
CREATE INDEX
    idx_INO_inspanl
ON
    BS_AEINSPECTION_ANL
    (
        AEINSPECTIONNO
    );
--BS_NOGENERATE
CREATE INDEX
    idx_orgno_nogent
ON
    BS_NOGENERATE
    (
        ORGNO
    );
--BS_EXAMSCORE
CREATE INDEX
    idx_peoid_exmsc
ON
    BS_EXAMSCORE
    (
        PEOID
    );
    
    
    
CREATE INDEX
    idx_org_parentNo
ON
    BS_org
    (
        ParentNo
    );