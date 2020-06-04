/**
 * UserCredential.java
 *
 * Created on March 24, 2014
 */
package aegwyn.core.cred.entity;

import java.io.BufferedInputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TypedQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.annotations.UuidGenerator;
//import sunwell.xrp.util.MainAppRuntime;
//import sunwell.xrp.util.PersistentObject;

/**
 * @author Yoga
 * @author Ahmad
 * @version 1.0
 * @created 24-Mar-2014 13:34:07
 */
@Entity
@Table (name = "usercredential")
@NamedQueries({
    @NamedQuery(name="UserCredential.findByUsername", query = "SELECT p FROM UserCredential p WHERE p.userName = :uname"),
    @NamedQuery(name="UserCredential.findBySystemId", query = "SELECT p FROM UserCredential p WHERE p.systemId = :id"),
    @NamedQuery(name="UserCredential.findByGroup", query="SELECT p FROM UserCredential p WHERE p.group = :group"),
    @NamedQuery(name="UserCredential.findAll", query="SELECT p FROM UserCredential p ORDER BY p.userName")
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserCredential implements Serializable
{       
    @Id
    @SequenceGenerator (name = "usercredential_systemid_seq", sequenceName = "usercredential_systemid_seq", allocationSize = 1)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "usercredential_systemid_seq" )
    @Column (name = "systemid")
    private Long systemId;
    
    // nggak bisa, jpa nggak bisa bekerja tanpa primary key yang dibuat otomatis di db
//    @Id
//    @Column(name="systemid", insertable = false)
//    private Long systemId; 
    
    @Column
    private String tenantId;
        
    @ManyToOne
    @JoinColumn (name = "group_id")
    private UserGroup group;
    
    @Column (name = "username")
    private String userName;
    
    @Column (name = "pwd")
    private String pwd;
    
    @Column (name = "systemaccessenabled")
    private boolean systemAccessEnabled;
    
    @Column (name = "systemmonitoringenabled")
    private boolean systemMonitoringEnabled;
    
    @Column (name = "expiredate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar expireDate;
    
    @Column (name = "notes")
    private String notes;
    
    @XmlTransient
    @ManyToOne
    @JoinColumn (name = "sys_creator")
    private UserCredential sysCreator;
    
    @Column (name = "sys_createdate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar sysCreateDate;
    
    @XmlTransient
    @ManyToOne
    @JoinColumn (name = "sys_lastupdater")
    private UserCredential sysLastUpdater;
    
    @Column (name = "sys_lastupdate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar sysLastUpdate;

    public UserCredential ()
    {
    }
    
    /**
     * @return the tenantId
     */
    public String getTenantId ()
    {
        return tenantId;
    }

    /**
     * @param tenantId the tenantId to set
     */
    public void setTenantId (String tenantId)
    {
        this.tenantId = tenantId;
    }

    /**
     * @return the systemId
     */
    public Long getSystemId ()
    {
        return systemId;
    }

    /**
     * @param systemId the systemId to set
     */
    public void setSystemId (Long systemId)
    {
        this.systemId = systemId;
    }
    
//    public UserCredential (long _id)
//    {
//        systemId = _id;
//    }
//
//    /**
//     * @return the m_systemid
//     */
//    public long getSystemId ()
//    {
//        return systemId;
//    }
//
//    /**
//     * @param m_systemid the m_systemid to set
//     */
//    public void setSystemId (long m_systemid)
//    {
//        this.systemId = m_systemid;
//    }

   
    /**
     * @return the m_group
     */
    public UserGroup getGroup ()
    {
        return group;
    }

    /**
     * @param m_group the m_group to set
     */
    public void setGroup (UserGroup m_group)
    {
        this.group = m_group;
    }

    /**
     * @return the m_username
     */
    public String getUserName ()
    {
        return userName;
    }

    /**
     * @param m_username the m_username to set
     */
    public void setUserName (String m_username)
    {
        this.userName = m_username;
    }

    /**
     * @return the m_pwd
     */
    public String getPwd ()
    {
        return pwd;
    }

    /**
     * String password yang dilewatkan akan di-MD5 dan yg disimpan sebagai atribut
     * adalah hasil MD5-nya.
     * 
     * @param _pwd the m_pwd to set
     */
    public void setPwd (String _pwd)
    {
        this.pwd = UserCredential.getChecksumOf (_pwd);
    }

    /**
     * @return the m_systemaccessenabled
     */
    public boolean isSystemAccessEnabled ()
    {
        return systemAccessEnabled;
    }

    /**
     * @param m_systemaccessenabled the m_systemaccessenabled to set
     */
    public void setSystemAccessEnabled (boolean m_systemaccessenabled)
    {
        this.systemAccessEnabled = m_systemaccessenabled;
    }

    /**
     * @return the m_systemmonitoringenabled
     */
    public boolean isSystemMonitoringEnabled ()
    {
        return systemMonitoringEnabled;
    }

    /**
     * @param m_systemmonitoringenabled the m_systemmonitoringenabled to set
     */
    public void setSystemMonitoringEnabled (boolean m_systemmonitoringenabled)
    {
        this.systemMonitoringEnabled = m_systemmonitoringenabled;
    }

    /**
     * @return the m_expiredate
     */
    public Calendar getExpireDate ()
    {
        return expireDate;
    }

    /**
     * @param m_expiredate the m_expiredate to set
     */
    public void setExpireDate (Calendar m_expiredate)
    {
        this.expireDate = m_expiredate;
    }

    /**
     * @return the m_notes
     */
    public String getNotes ()
    {
        return notes;
    }

    /**
     * @param m_notes the m_notes to set
     */
    public void setNotes (String m_notes)
    {
        this.notes = m_notes;
    }

    /**
     * @return the m_sys_creator
     */
    public UserCredential getSysCreator ()
    {
        return sysCreator;
    }

    /**
     * @param m_sys_creator the m_sys_creator to set
     */
    public void setSysCreator (UserCredential m_sys_creator)
    {
        this.sysCreator = m_sys_creator;
    }

    /**
     * @return the m_sys_createdate
     */
    public Calendar getSysCreateDate ()
    {
        return sysCreateDate;
    }

    /**
     * @param m_sys_createdate the m_sys_createdate to set
     */
    public void setSysCreateDate (Calendar m_sys_createdate)
    {
        this.sysCreateDate = m_sys_createdate;
    }

    /**
     * @return the m_sys_lastupdater
     */
    public UserCredential getSysLastUpdater ()
    {
        return sysLastUpdater;
    }

    /**
     * @param m_sys_lastupdater the m_sys_lastupdater to set
     */
    public void setSysLastUpdater (UserCredential m_sys_lastupdater)
    {
        this.sysLastUpdater = m_sys_lastupdater;
    }

    /**
     * @return the m_sys_lastupdate
     */
    public Calendar getSysLastUpdate ()
    {
        return sysLastUpdate;
    }

    /**
     * @param m_sys_lastupdate the m_sys_lastupdate to set
     */
    public void setSysLastUpdate (Calendar m_sys_lastupdate)
    {
        this.sysLastUpdate = m_sys_lastupdate;
    }
    
    /**
     * Memeriksa apakah terdapat username {@code _username} dgn password {@code _passwd}
     * di dalam sistem.
     * 
     * <br><br>
     * Catatan:<br>
     * Return value berupa integer, bukan boolean, utk antisipasi jika kemudian hari
     * membutuhkan nilai lebih dari 2 (bkn hanya true dan false).
     * 
     * @param _username
     * @param _passwd
     * @return integer 1 jika autentikasi sukses, 0 jika gagal.
     * @throws SQLException 
     */
    public static int authenticate (String _username, String _passwd) throws SQLException
    {
        //
        return 1;
    }
    
//    public static UserCredential queryByUsernameAndPass (String _u, String _p) throws SQLException
//    {
//        UserCredential retval;
//        PreparedStatement ps = null;
//        ResultSet rs;
//        StringBuilder sql = new StringBuilder ();
//        EntityManager em = MainAppRuntime.getEntityManager ();
//        int sysid;
//        
//        sql.append ("SELECT systemid ");
//        sql.append ("FROM usercredential ");
//        sql.append ("WHERE (username = ?) AND (pwd = md5(?))");
//        
//        try {
//            ps = MainAppRuntime.getDBConnection ().getConnection ().prepareStatement (sql.toString ());
//            ps.setString (1, _u);
//            ps.setString (2, _p);
//            
//            rs = ps.executeQuery ();
//            if (! rs.next ())
//                return null;
//            
//            retval = em.find (UserCredential.class, rs.getInt (1));
//        }
//        catch (SQLException e) {
//            MainAppRuntime.getDBConnection ().getConnection ().rollback ();
//            throw e;
//        }
//        
//        return retval;
//    }

    @Override
    public int hashCode ()
    {
        if(getTenantId () == null)
            return 0;
        
        return getTenantId ().hashCode ();
    }

    @Override
    public boolean equals (Object obj)
    {
        if (!(obj instanceof UserCredential)) {
            return false;
        }
        
        UserCredential other = (UserCredential) obj;
        if ((this.getTenantId () == null && other.getTenantId () != null) || 
            (this.getTenantId () != null && !this.tenantId.equals(other.tenantId))) {
            return false;
        }
        return false;
    }
    
//    @Override
//    public void saveAsNewToDB() throws java.sql.SQLException
//    {
//        PreparedStatement ps = null;
//        String query;
//
//        query = "INSERT INTO usercredential (username, group_id, pwd) VALUES (?, ?, ?)";
//        try {
//            ps = MainAppRuntime.getDBConnection().getConnection().prepareStatement(query);
//            ps.setString(1, m_username);
//            ps.setInt (2, m_group.getSystemId ());
//            ps.setString(3, m_pwd);
//
//            ps.executeUpdate();
//        }
//        catch (SQLException e) {
//            MainAppRuntime.getDBConnection().getConnection().rollback();
//            throw e;
//        }
//        finally {
//            if (ps != null)
//                try { ps.close(); } catch (SQLException x) {}
//        }
//    }
//    
//    @Override
//    public boolean updateToDB (Object _newObjValue) throws java.sql.SQLException 
//    {
//        UserCredential newObj = null;
//        PreparedStatement ps = null;
//        String query = null;
//        int n;
//
//        if (!(_newObjValue instanceof UserCredential)) {
//            throw new SQLException("Tipe kelas objek tidak sama");
//        }
//        newObj = (UserCredential) _newObjValue;
//
//        query = "UPDATE usercredential SET "
//                + " username = ?, "
//                + " group_id = ?, "
//                + " pwd = ?, "       // ingat atribut pwd sudah ter-MD5
//                + " sys_lastupdate = xrpGetCurrentTimestamp() "
//                + "WHERE systemid = ?";
//        try {
//            ps = MainAppRuntime.getDBConnection().getConnection().prepareStatement(query);
//            ps.setString(1, newObj.m_username);
//            ps.setInt(2, newObj.m_group.getSystemId ());
//            ps.setString(3, newObj.m_pwd);
//
//            ps.setInt (4, this.m_systemId);
//
//            n = ps.executeUpdate();
//
//            return (n > 0);
//        } 
//        catch (SQLException e) {
//            MainAppRuntime.getDBConnection().getConnection().rollback();
//            throw e;
//        }
//        finally {
//            if (ps != null)
//                try { ps.close(); } catch (SQLException x) {}
//        }
//    }
//    
//    @Override
//    public boolean deleteFromDB() throws java.sql.SQLException 
//    {
//        PreparedStatement ps = null;
//        String query = null;
//        int n;
//
//        query = "DELETE FROM usercredential WHERE systemid = ?";
//        try {
//            ps = MainAppRuntime.getDBConnection().getConnection().prepareStatement(query);
//            ps.setInt(1, m_systemId);
//
//            n = ps.executeUpdate();
//
//            return (n > 0);
//        } 
//        catch (SQLException e) {
//            MainAppRuntime.getDBConnection().getConnection().rollback();
//            throw e;
//        } 
//        finally {
//            if (ps != null)
//                try { ps.close(); } catch (SQLException x) {}
//        }
//    }
//    
//    public static UserCredential findById (int _id)
//    {
//        EntityManager em = MainAppRuntime.getEntityManager ();
//        TypedQuery<UserCredential> query = em.createNamedQuery ("UserCredential.findBySystemId", UserCredential.class);
//        query.setParameter ("sysid", _id);
//        
//        return query.getSingleResult ();
//    }
//    
//    @Deprecated
//    public static UserCredential queryByID(int _systemid) throws SQLException 
//    {
//        UserCredential retval = null;
//        PreparedStatement ps = null;
//        String query = null;
//        ResultSet rs = null;
//
//        query = "SELECT systemid, username, group_id, pwd FROM usercredential "
//                + "WHERE systemid = ?";
//        try {
//            ps = MainAppRuntime.getDBConnection().getConnection().prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
//            ps.setInt(1, _systemid);
//
//            rs = ps.executeQuery();
//            if (!rs.next())
//                return null;
//
//            retval = new UserCredential ();
//            retval.setSystemId (_systemid);
//            retval.setUserName (rs.getString("username"));
//            
//            UserGroup usrGrp;
//            int idUG = rs.getInt (3);
//
//            usrGrp = MainAppRuntime.getEntityManager ().find (UserGroup.class, idUG);
//            retval.setGroup (usrGrp);
//            
//            retval.setPwd (rs.getString(2));
//
//            return retval;
//        } 
//        catch (SQLException e) {
//            MainAppRuntime.getDBConnection().getConnection().rollback();
//            throw e;
//        }
//        finally {
//            if (ps != null)
//                try { ps.close(); } catch (SQLException x) { }
//        }
//    }
//
//    @Override
//    public boolean isExistsInDB () throws SQLException
//    {
//        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
//    public static boolean validateForLogin (String _username, String _passwdToBeMD5, StringBuilder _validationFailReason) throws SQLException
//    {
//        PreparedStatement ps = null;
//        String query;
//        ResultSet rs;
//        String storedPasswd;
//
//        // Proses:
//        // 1. Query username dan passwd dari table userlogin berdasarkan nilai
//        //     argumen _username.
//        // 2. Query apakah nilai MD5 dari argumen _passwdToBeMD5 sama dgn nilai
//        //     field passwd di table.
//        // 3. Jika username dan passwd yg diberikan benar, cek apakah status
//        //     UserLogin ini telah expire atau belum. Pengecekan dilakukan dgn
//        //     mengacu pada jam di server DB, JANGAN pada jam di komputer lokal.
//
//        query = "SELECT pwd FROM usercredential WHERE (username = ?)";
//        try {
//            ps = MainAppRuntime.getDBConnection ().getConnection ().prepareStatement (query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
//            ps.setString (1, _username);
//
//            rs = ps.executeQuery ();
//            if (! rs.next ()) {
//                _validationFailReason.append ("");
//                return false;
//            }
//
//            storedPasswd = rs.getString (1);
//            ps.close ();
//
//            // Bandingkan password
//            if (! getChecksumOf (_passwdToBeMD5).equals (storedPasswd)) {
//                _validationFailReason.append ("Password yang dimasukkan salah");
//                return false;
//            }
//
//            return true;
//        }
//        catch (SQLException e) {
//            MainAppRuntime.getDBConnection ().getConnection ().rollback ();
//            throw e;
//        }
//        finally {
//            if (ps != null)
//                try { ps.close (); } catch (SQLException t) { }
//        }
//    }
    
    private static String getChecksumOf(String _strToCheck) 
    {
        // this getchecksum does not put : delimiters inside results
        try {
            StringBuffer buf = new StringBuffer();
            byte[] digestBuffer = new byte[8];
            byte[] digest = null;
            BufferedInputStream bis;
            MessageDigest md5;
            int i;
            md5 = MessageDigest.getInstance("MD5");
            md5.update(_strToCheck.getBytes());
            digest = md5.digest();
            int len = digest.length;
            for (int j = 0; j < len; j++) {
                byte2hex(digest[j], buf);
            }
            
            return buf.toString().toLowerCase ();
        } 
        catch (Exception e) {
            e.printStackTrace (System.out);
        }
        return null;
    }
    
    private static void byte2hex(byte b, StringBuffer buf) 
    {
        char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        int high = (b & 0xf0) >> 4;
        int low = b & 0x0f;
        buf.append(hexChars[high]);
        buf.append(hexChars[low]);
    }
    
//    public static UserCredential[] getAllInArray ()
//    {
//        UserCredential[] result;
//        List<UserCredential> ucList;
//        
//        EntityManager em = MainAppRuntime.getEntityManager ();
//        ucList = em.createNamedQuery ("UserCredential.findAll").getResultList ();
//        
//        result = new UserCredential [ucList.size ()];
//        
//        return ucList.toArray (result);
//    }
}
