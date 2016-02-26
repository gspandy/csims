package com.gtm.csims.model.base;

import java.io.Serializable;

/**
 * This is an object that contains data related to the BS_ORG table. Do not
 * modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * 
 * @hibernate.class table="BS_ORG"
 */

public abstract class BaseBsOrg implements Serializable {

    public static String REF = "BsOrg";
    public static String PROP_O = "O";
    public static String PROP_W = "W";
    public static String PROP_PARENTNAME = "Parentname";
    public static String PROP_H = "H";
    public static String PROP_PCBNO = "Pcbno";
    public static String PROP_P = "P";
    public static String PROP_V = "V";
    public static String PROP_PARENTNO = "Parentno";
    public static String PROP_G = "G";
    public static String PROP_PCBNAME = "Pcbname";
    public static String PROP_I = "I";
    public static String PROP_AD = "Ad";
    public static String PROP_AA = "Aa";
    public static String PROP_A = "A";
    public static String PROP_F = "F";
    public static String PROP_U = "U";
    public static String PROP_ISPCB = "Ispcb";
    public static String PROP_N = "N";
    public static String PROP_ORGSTATUS = "Orgstatus";
    public static String PROP_ORGCODEOFZZ = "OrgCodeOfZz";
    public static String PROP_NAME = "Name";
    public static String PROP_AF = "Af";
    public static String PROP_AC = "Ac";
    public static String PROP_X = "X";
    public static String PROP_Z = "Z";
    public static String PROP_S = "S";
    public static String PROP_D = "D";
    public static String PROP_R = "R";
    public static String PROP_K = "K";
    public static String PROP_AB = "Ab";
    public static String PROP_C = "C";
    public static String PROP_ORGCODEOFXY = "OrgCodeOfXy";
    public static String PROP_B = "B";
    public static String PROP_AE = "Ae";
    public static String PROP_L = "L";
    public static String PROP_Q = "Q";
    public static String PROP_M = "M";
    public static String PROP_AG = "Ag";
    public static String PROP_Y = "Y";
    public static String PROP_J = "J";
    public static String PROP_T = "T";
    public static String PROP_E = "E";
    public static String PROP_NO = "NO";

    // constructors
    public BaseBsOrg() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseBsOrg(java.lang.String no) {
        this.setNo(no);
        initialize();
    }

    protected void initialize() {
    }

    private int hashCode = Integer.MIN_VALUE;

    // primary key
    private java.lang.String no;

    // fields
    private java.lang.String name;
    private java.lang.String parentno;
    private java.lang.String parentname;
    private java.lang.String ispcb;
    private java.lang.String orgCodeOfZz;
    private java.lang.String orgCodeOfXy;
    private java.lang.String pcbno;
    private java.lang.String pcbname;
    private java.lang.String a;
    private java.lang.String b;
    private java.lang.String c;
    private java.lang.String d;
    private java.lang.String e;
    private java.lang.String f;
    private java.lang.String g;
    private java.lang.String h;
    private java.lang.String i;
    private java.lang.String j;
    private java.lang.String k;
    private java.lang.String l;
    private java.lang.String m;
    private java.lang.String n;
    private java.lang.String o;
    private java.lang.String p;
    private java.lang.String q;
    private java.lang.String r;
    private java.lang.String s;
    private java.lang.String t;
    private java.lang.String u;
    private java.lang.String v;
    private java.lang.String w;
    private java.lang.String x;
    private java.lang.String y;
    private java.lang.String z;
    private java.lang.String aa;
    private java.lang.String ab;
    private java.lang.String ac;
    private java.lang.String ad;
    private java.lang.String ae;
    private java.lang.String af;
    private java.lang.String ag;
    private java.lang.String orgstatus;

    /**
     * Return the unique identifier of this class
     * 
     * @hibernate.id generator-class="org.hibernate.id.UUIDHexGenerator"
     *               column="NO"
     */
    public java.lang.String getNo() {
        return no;
    }

    /**
     * Set the unique identifier of this class
     * 
     * @param id
     *            the new ID
     */
    public void setNo(java.lang.String no) {
        this.no = no;
        this.hashCode = Integer.MIN_VALUE;
    }

    /**
     * Return the value associated with the column: NAME
     */
    public java.lang.String getName() {
        return name;
    }

    /**
     * Set the value related to the column: NAME
     * 
     * @param name
     *            the NAME value
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    /**
     * Return the value associated with the column: PARENTNO
     */
    public java.lang.String getParentno() {
        return parentno;
    }

    /**
     * Set the value related to the column: PARENTNO
     * 
     * @param parentno
     *            the PARENTNO value
     */
    public void setParentno(java.lang.String parentno) {
        this.parentno = parentno;
    }

    /**
     * Return the value associated with the column: PARENTNAME
     */
    public java.lang.String getParentname() {
        return parentname;
    }

    /**
     * Set the value related to the column: PARENTNAME
     * 
     * @param parentname
     *            the PARENTNAME value
     */
    public void setParentname(java.lang.String parentname) {
        this.parentname = parentname;
    }

    /**
     * Return the value associated with the column: ISPCB
     */
    public java.lang.String getIspcb() {
        return ispcb;
    }

    /**
     * Set the value related to the column: ISPCB
     * 
     * @param ispcb
     *            the ISPCB value
     */
    public void setIspcb(java.lang.String ispcb) {
        this.ispcb = ispcb;
    }

    /**
     * Return the value associated with the column: ORGCODEOFZZ
     */
    public java.lang.String getOrgCodeOfZz() {
        return orgCodeOfZz;
    }

    /**
     * Set the value related to the column: ORGCODEOFZZ
     * 
     * @param orgcodeofzz
     *            the ORGCODEOFZZ value
     */
    public void setOrgCodeOfZz(java.lang.String orgcodeofzz) {
        this.orgCodeOfZz = orgcodeofzz;
    }

    /**
     * Return the value associated with the column: ORGCODEOFXY
     */
    public java.lang.String getOrgCodeOfXy() {
        return orgCodeOfXy;
    }

    /**
     * Set the value related to the column: ORGCODEOFXY
     * 
     * @param orgcodeofxy
     *            the ORGCODEOFXY value
     */
    public void setOrgCodeOfXy(java.lang.String orgcodeofxy) {
        this.orgCodeOfXy = orgcodeofxy;
    }

    /**
     * Return the value associated with the column: PCBNO
     */
    public java.lang.String getPcbno() {
        return pcbno;
    }

    /**
     * Set the value related to the column: PCBNO
     * 
     * @param pcbno
     *            the PCBNO value
     */
    public void setPcbno(java.lang.String pcbno) {
        this.pcbno = pcbno;
    }

    /**
     * Return the value associated with the column: PCBNAME
     */
    public java.lang.String getPcbname() {
        return pcbname;
    }

    /**
     * Set the value related to the column: PCBNAME
     * 
     * @param pcbname
     *            the PCBNAME value
     */
    public void setPcbname(java.lang.String pcbname) {
        this.pcbname = pcbname;
    }

    /**
     * Return the value associated with the column: A
     */
    public java.lang.String getA() {
        return a;
    }

    /**
     * Set the value related to the column: A
     * 
     * @param a
     *            the A value
     */
    public void setA(java.lang.String a) {
        this.a = a;
    }

    /**
     * Return the value associated with the column: B
     */
    public java.lang.String getB() {
        return b;
    }

    /**
     * Set the value related to the column: B
     * 
     * @param b
     *            the B value
     */
    public void setB(java.lang.String b) {
        this.b = b;
    }

    /**
     * Return the value associated with the column: C
     */
    public java.lang.String getC() {
        return c;
    }

    /**
     * Set the value related to the column: C
     * 
     * @param c
     *            the C value
     */
    public void setC(java.lang.String c) {
        this.c = c;
    }

    /**
     * Return the value associated with the column: D
     */
    public java.lang.String getD() {
        return d;
    }

    /**
     * Set the value related to the column: D
     * 
     * @param d
     *            the D value
     */
    public void setD(java.lang.String d) {
        this.d = d;
    }

    /**
     * Return the value associated with the column: E
     */
    public java.lang.String getE() {
        return e;
    }

    /**
     * Set the value related to the column: E
     * 
     * @param e
     *            the E value
     */
    public void setE(java.lang.String e) {
        this.e = e;
    }

    /**
     * Return the value associated with the column: F
     */
    public java.lang.String getF() {
        return f;
    }

    /**
     * Set the value related to the column: F
     * 
     * @param f
     *            the F value
     */
    public void setF(java.lang.String f) {
        this.f = f;
    }

    /**
     * Return the value associated with the column: G
     */
    public java.lang.String getG() {
        return g;
    }

    /**
     * Set the value related to the column: G
     * 
     * @param g
     *            the G value
     */
    public void setG(java.lang.String g) {
        this.g = g;
    }

    /**
     * Return the value associated with the column: H
     */
    public java.lang.String getH() {
        return h;
    }

    /**
     * Set the value related to the column: H
     * 
     * @param h
     *            the H value
     */
    public void setH(java.lang.String h) {
        this.h = h;
    }

    /**
     * Return the value associated with the column: I
     */
    public java.lang.String getI() {
        return i;
    }

    /**
     * Set the value related to the column: I
     * 
     * @param i
     *            the I value
     */
    public void setI(java.lang.String i) {
        this.i = i;
    }

    /**
     * Return the value associated with the column: J
     */
    public java.lang.String getJ() {
        return j;
    }

    /**
     * Set the value related to the column: J
     * 
     * @param j
     *            the J value
     */
    public void setJ(java.lang.String j) {
        this.j = j;
    }

    /**
     * Return the value associated with the column: K
     */
    public java.lang.String getK() {
        return k;
    }

    /**
     * Set the value related to the column: K
     * 
     * @param k
     *            the K value
     */
    public void setK(java.lang.String k) {
        this.k = k;
    }

    /**
     * Return the value associated with the column: L
     */
    public java.lang.String getL() {
        return l;
    }

    /**
     * Set the value related to the column: L
     * 
     * @param l
     *            the L value
     */
    public void setL(java.lang.String l) {
        this.l = l;
    }

    /**
     * Return the value associated with the column: M
     */
    public java.lang.String getM() {
        return m;
    }

    /**
     * Set the value related to the column: M
     * 
     * @param m
     *            the M value
     */
    public void setM(java.lang.String m) {
        this.m = m;
    }

    /**
     * Return the value associated with the column: N
     */
    public java.lang.String getN() {
        return n;
    }

    /**
     * Set the value related to the column: N
     * 
     * @param n
     *            the N value
     */
    public void setN(java.lang.String n) {
        this.n = n;
    }

    /**
     * Return the value associated with the column: O
     */
    public java.lang.String getO() {
        return o;
    }

    /**
     * Set the value related to the column: O
     * 
     * @param o
     *            the O value
     */
    public void setO(java.lang.String o) {
        this.o = o;
    }

    /**
     * Return the value associated with the column: P
     */
    public java.lang.String getP() {
        return p;
    }

    /**
     * Set the value related to the column: P
     * 
     * @param p
     *            the P value
     */
    public void setP(java.lang.String p) {
        this.p = p;
    }

    /**
     * Return the value associated with the column: Q
     */
    public java.lang.String getQ() {
        return q;
    }

    /**
     * Set the value related to the column: Q
     * 
     * @param q
     *            the Q value
     */
    public void setQ(java.lang.String q) {
        this.q = q;
    }

    /**
     * Return the value associated with the column: R
     */
    public java.lang.String getR() {
        return r;
    }

    /**
     * Set the value related to the column: R
     * 
     * @param r
     *            the R value
     */
    public void setR(java.lang.String r) {
        this.r = r;
    }

    /**
     * Return the value associated with the column: S
     */
    public java.lang.String getS() {
        return s;
    }

    /**
     * Set the value related to the column: S
     * 
     * @param s
     *            the S value
     */
    public void setS(java.lang.String s) {
        this.s = s;
    }

    /**
     * Return the value associated with the column: T
     */
    public java.lang.String getT() {
        return t;
    }

    /**
     * Set the value related to the column: T
     * 
     * @param t
     *            the T value
     */
    public void setT(java.lang.String t) {
        this.t = t;
    }

    /**
     * Return the value associated with the column: U
     */
    public java.lang.String getU() {
        return u;
    }

    /**
     * Set the value related to the column: U
     * 
     * @param u
     *            the U value
     */
    public void setU(java.lang.String u) {
        this.u = u;
    }

    /**
     * Return the value associated with the column: V
     */
    public java.lang.String getV() {
        return v;
    }

    /**
     * Set the value related to the column: V
     * 
     * @param v
     *            the V value
     */
    public void setV(java.lang.String v) {
        this.v = v;
    }

    /**
     * Return the value associated with the column: W
     */
    public java.lang.String getW() {
        return w;
    }

    /**
     * Set the value related to the column: W
     * 
     * @param w
     *            the W value
     */
    public void setW(java.lang.String w) {
        this.w = w;
    }

    /**
     * Return the value associated with the column: X
     */
    public java.lang.String getX() {
        return x;
    }

    /**
     * Set the value related to the column: X
     * 
     * @param x
     *            the X value
     */
    public void setX(java.lang.String x) {
        this.x = x;
    }

    /**
     * Return the value associated with the column: Y
     */
    public java.lang.String getY() {
        return y;
    }

    /**
     * Set the value related to the column: Y
     * 
     * @param y
     *            the Y value
     */
    public void setY(java.lang.String y) {
        this.y = y;
    }

    /**
     * Return the value associated with the column: Z
     */
    public java.lang.String getZ() {
        return z;
    }

    /**
     * Set the value related to the column: Z
     * 
     * @param z
     *            the Z value
     */
    public void setZ(java.lang.String z) {
        this.z = z;
    }

    /**
     * Return the value associated with the column: AA
     */
    public java.lang.String getAa() {
        return aa;
    }

    /**
     * Set the value related to the column: AA
     * 
     * @param aa
     *            the AA value
     */
    public void setAa(java.lang.String aa) {
        this.aa = aa;
    }

    /**
     * Return the value associated with the column: AB
     */
    public java.lang.String getAb() {
        return ab;
    }

    /**
     * Set the value related to the column: AB
     * 
     * @param ab
     *            the AB value
     */
    public void setAb(java.lang.String ab) {
        this.ab = ab;
    }

    /**
     * Return the value associated with the column: AC
     */
    public java.lang.String getAc() {
        return ac;
    }

    /**
     * Set the value related to the column: AC
     * 
     * @param ac
     *            the AC value
     */
    public void setAc(java.lang.String ac) {
        this.ac = ac;
    }

    /**
     * Return the value associated with the column: AD
     */
    public java.lang.String getAd() {
        return ad;
    }

    /**
     * Set the value related to the column: AD
     * 
     * @param ad
     *            the AD value
     */
    public void setAd(java.lang.String ad) {
        this.ad = ad;
    }

    /**
     * Return the value associated with the column: AE
     */
    public java.lang.String getAe() {
        return ae;
    }

    /**
     * Set the value related to the column: AE
     * 
     * @param ae
     *            the AE value
     */
    public void setAe(java.lang.String ae) {
        this.ae = ae;
    }

    /**
     * Return the value associated with the column: AF
     */
    public java.lang.String getAf() {
        return af;
    }

    /**
     * Set the value related to the column: AF
     * 
     * @param af
     *            the AF value
     */
    public void setAf(java.lang.String af) {
        this.af = af;
    }

    /**
     * Return the value associated with the column: AG
     */
    public java.lang.String getAg() {
        return ag;
    }

    /**
     * Set the value related to the column: AG
     * 
     * @param ag
     *            the AG value
     */
    public void setAg(java.lang.String ag) {
        this.ag = ag;
    }

    /**
     * Return the value associated with the column: ORGSTATUS
     */
    public java.lang.String getOrgstatus() {
        return orgstatus;
    }

    /**
     * Set the value related to the column: ORGSTATUS
     * 
     * @param orgstatus
     *            the ORGSTATUS value
     */
    public void setOrgstatus(java.lang.String orgstatus) {
        this.orgstatus = orgstatus;
    }

    public boolean equals(Object obj) {
        if (null == obj)
            return false;
        if (!(obj instanceof com.gtm.csims.model.BsOrg))
            return false;
        else {
            com.gtm.csims.model.BsOrg bsOrg = (com.gtm.csims.model.BsOrg) obj;
            if (null == this.getNo() || null == bsOrg.getNo())
                return false;
            else
                return (this.getNo().equals(bsOrg.getNo()));
        }
    }

    public int hashCode() {
        if (Integer.MIN_VALUE == this.hashCode) {
            if (null == this.getNo())
                return super.hashCode();
            else {
                String hashStr = this.getClass().getName() + ":"
                        + this.getNo().hashCode();
                this.hashCode = hashStr.hashCode();
            }
        }
        return this.hashCode;
    }

    public String toString() {
        return super.toString();
    }

}