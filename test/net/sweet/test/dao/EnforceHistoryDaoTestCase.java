package net.sweet.test.dao;

import java.util.Date;
import java.util.List;

import net.sweet.test.base.ApplicationContextTest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.gtm.csims.dao.BsAdmenforceDAO;
import com.gtm.csims.dao.BsAePeopleJoinHistoryDAO;
import com.gtm.csims.dao.BsAeedOrgJoinHistoryDAO;
import com.gtm.csims.dao.BsAepeopleDAO;
import com.gtm.csims.dao.BsOrgDAO;
import com.gtm.csims.model.BsAdmenforce;
import com.gtm.csims.model.BsAePeopleJoinHistory;
import com.gtm.csims.model.BsAeedOrgJoinHistory;
import com.gtm.csims.model.BsAepeople;
import com.gtm.csims.model.BsOrg;

public class EnforceHistoryDaoTestCase extends ApplicationContextTest {

	/**
	 * 日志调试.
	 */
	private static Logger LOGGER = Logger.getLogger(EnforceHistoryDaoTestCase.class);

	private BsAePeopleJoinHistoryDAO bsAePeopleJoinHistoryDao;

	private BsAdmenforceDAO bsAdmenforceDao;

	private BsAepeopleDAO bsAepeopleDao;

	private BsOrgDAO bsOrgDao;

	private BsAeedOrgJoinHistoryDAO BsAeedOrgJoinHistoryDao;

	public void setBsAePeopleJoinHistoryDao(BsAePeopleJoinHistoryDAO bsAePeopleJoinHistoryDao) {
		this.bsAePeopleJoinHistoryDao = bsAePeopleJoinHistoryDao;
	}

	public void setBsAeedOrgJoinHistoryDao(BsAeedOrgJoinHistoryDAO bsAeedOrgJoinHistoryDao) {
		BsAeedOrgJoinHistoryDao = bsAeedOrgJoinHistoryDao;
	}

	public void setBsAdmenforceDao(BsAdmenforceDAO bsAdmenforceDao) {
		this.bsAdmenforceDao = bsAdmenforceDao;
	}

	public void setBsAepeopleDao(BsAepeopleDAO bsAepeopleDao) {
		this.bsAepeopleDao = bsAepeopleDao;
	}

	public void setBsOrgDao(BsOrgDAO bsOrgDao) {
		this.bsOrgDao = bsOrgDao;
	}

	public void test_savePeopleHistory() {
		List<BsAdmenforce> allEnforces = bsAdmenforceDao.getAll();

		for (BsAdmenforce bsAdmenforce : allEnforces) {
			String others = bsAdmenforce.getAeother();
			String[] everyOther = StringUtils.split(others, ",");
			for (String otherStr : everyOther) {
				String name = StringUtils.split(otherStr, "--")[0];
				String certNo = StringUtils.split(otherStr, "--")[1];
				List<BsAepeople> peopleList = bsAepeopleDao.find("from BsAepeople where certNo = ?", certNo);
				if (CollectionUtils.isEmpty(peopleList)) {
					LOGGER.debug("people is null, certno is  " + certNo);
				} else {
					BsAepeople people = peopleList.get(0);
					BsAePeopleJoinHistory pHistory = new BsAePeopleJoinHistory();
					pHistory.setAeedorgnm(bsAdmenforce.getAeedorgnm());
					pHistory.setAeedorgno(bsAdmenforce.getAeedorgno());
					pHistory.setAeEnforceCreateDate(bsAdmenforce.getCreatedate());
					pHistory.setAeno(bsAdmenforce.getAeno());
					pHistory.setAeorgnm(bsAdmenforce.getAeorgnm());
					pHistory.setAeorgno(bsAdmenforce.getAeorgno());
					pHistory.setAeplanstdate(bsAdmenforce.getAeplanstdate());
					pHistory.setAeplantmdate(bsAdmenforce.getAeplantmdate());

					pHistory.setCardid(people.getCardid());
					pHistory.setCertno(people.getCertno());
					pHistory.setCerttp(people.getCerttp());
					pHistory.setDptnm(people.getDptnm());
					pHistory.setOrgnm(people.getOrgnm());
					pHistory.setOrgno(people.getOrgno());
					pHistory.setPepname(people.getPepname());
					pHistory.setPricipal(1);

					pHistory.setFlag(StringUtils.EMPTY);
					pHistory.setStat(StringUtils.EMPTY);
					Date now = new Date();
					pHistory.setCreatedate(now);
					pHistory.setUpdatedate(now);

					bsAePeopleJoinHistoryDao.save(pHistory);
				}

			}

			String masters = bsAdmenforce.getAemaster();
			String[] everyMaster = StringUtils.split(masters, ",");
			for (String masterStr : everyMaster) {
				String name = StringUtils.split(masterStr, "--")[0];
				String certNo = StringUtils.split(masterStr, "--")[1];
				List<BsAepeople> peopleList = bsAepeopleDao.find("from BsAepeople where certNo = ?", certNo);
				if (CollectionUtils.isEmpty(peopleList)) {
					LOGGER.debug("people is null, certno is  " + certNo);
				} else {
					BsAepeople people = peopleList.get(0);
					BsAePeopleJoinHistory pHistory = new BsAePeopleJoinHistory();
					pHistory.setAeedorgnm(bsAdmenforce.getAeedorgnm());
					pHistory.setAeedorgno(bsAdmenforce.getAeedorgno());
					pHistory.setAeEnforceCreateDate(bsAdmenforce.getCreatedate());
					pHistory.setAeno(bsAdmenforce.getAeno());
					pHistory.setAeorgnm(bsAdmenforce.getAeorgnm());
					pHistory.setAeorgno(bsAdmenforce.getAeorgno());
					pHistory.setAeplanstdate(bsAdmenforce.getAeplanstdate());
					pHistory.setAeplantmdate(bsAdmenforce.getAeplantmdate());

					pHistory.setCardid(people.getCardid());
					pHistory.setCertno(people.getCertno());
					pHistory.setCerttp(people.getCerttp());
					pHistory.setDptnm(people.getDptnm());
					pHistory.setOrgnm(people.getOrgnm());
					pHistory.setOrgno(people.getOrgno());
					pHistory.setPepname(people.getPepname());
					pHistory.setPricipal(2);

					pHistory.setFlag(StringUtils.EMPTY);
					pHistory.setStat(StringUtils.EMPTY);
					Date now = new Date();
					pHistory.setCreatedate(now);
					pHistory.setUpdatedate(now);

					bsAePeopleJoinHistoryDao.save(pHistory);
				}

			}

			String headmans = bsAdmenforce.getAeheadman();
			String[] everyheadman = StringUtils.split(headmans, ",");
			for (String headmanStr : everyheadman) {
				String name = StringUtils.split(headmanStr, "--")[0];
				String certNo = StringUtils.split(headmanStr, "--")[1];
				List<BsAepeople> peopleList = bsAepeopleDao.find("from BsAepeople where certNo = ?", certNo);
				if (CollectionUtils.isEmpty(peopleList)) {
					LOGGER.debug("people is null, certno is  " + certNo);
				} else {
					BsAepeople people = peopleList.get(0);
					BsAePeopleJoinHistory pHistory = new BsAePeopleJoinHistory();
					pHistory.setAeedorgnm(bsAdmenforce.getAeedorgnm());
					pHistory.setAeedorgno(bsAdmenforce.getAeedorgno());
					pHistory.setAeEnforceCreateDate(bsAdmenforce.getCreatedate());
					pHistory.setAeno(bsAdmenforce.getAeno());
					pHistory.setAeorgnm(bsAdmenforce.getAeorgnm());
					pHistory.setAeorgno(bsAdmenforce.getAeorgno());
					pHistory.setAeplanstdate(bsAdmenforce.getAeplanstdate());
					pHistory.setAeplantmdate(bsAdmenforce.getAeplantmdate());

					pHistory.setCardid(people.getCardid());
					pHistory.setCertno(people.getCertno());
					pHistory.setCerttp(people.getCerttp());
					pHistory.setDptnm(people.getDptnm());
					pHistory.setOrgnm(people.getOrgnm());
					pHistory.setOrgno(people.getOrgno());
					pHistory.setPepname(people.getPepname());
					pHistory.setPricipal(3);

					pHistory.setFlag(StringUtils.EMPTY);
					pHistory.setStat(StringUtils.EMPTY);
					Date now = new Date();
					pHistory.setCreatedate(now);
					pHistory.setUpdatedate(now);

					bsAePeopleJoinHistoryDao.save(pHistory);
				}

			}
		}
	}

	public void test_saveOrgHistory() {
		List<BsAdmenforce> allEnforces = bsAdmenforceDao.getAll();

		for (BsAdmenforce bsAdmenforce : allEnforces) {
			String aeedorgs = bsAdmenforce.getAeedorgno();
			String[] everyAeedorg = StringUtils.split(aeedorgs, ",");
			for (String aeedorgNo : everyAeedorg) {
				BsOrg aeedorg = bsOrgDao.get(aeedorgNo);
				if (aeedorg == null) {
					LOGGER.debug("aeedorg is null, orgNo is  " + aeedorgNo);
				} else {
					BsAeedOrgJoinHistory oHistory = new BsAeedOrgJoinHistory();

					oHistory.setAeno(bsAdmenforce.getAeno());
					oHistory.setAeorgnm(bsAdmenforce.getAeorgnm());
					oHistory.setAeorgno(bsAdmenforce.getAeorgno());

					oHistory.setAeedorgnm(aeedorg.getName());
					oHistory.setAeedorgno(aeedorg.getNo());

					oHistory.setAeedorgtypeno(aeedorg.getI());
					oHistory.setAeplanstdate(bsAdmenforce.getAeplanstdate());
					oHistory.setAeplantmdate(bsAdmenforce.getAeplantmdate());
					oHistory.setAeEnforceCreateDate(bsAdmenforce.getCreatedate());

					oHistory.setPricipal(1);

					oHistory.setFlag(StringUtils.EMPTY);
					oHistory.setStat(StringUtils.EMPTY);
					Date now = new Date();
					oHistory.setCreatedate(now);
					oHistory.setUpdatedate(now);

					BsAeedOrgJoinHistoryDao.save(oHistory);
				}

			}
		}
	}
}
