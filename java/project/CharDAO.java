package chr;


import java.sql.*;
import java.util.*;
import chr.*;
import chr.CharDTO;
import chr.CharDAO;

import java.util.List.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CharDAO {

	Connection conn;
	PreparedStatement ps;
	ResultSet rs;

	String url, user, pass;
	public CharDAO() {
	try{
	Class.forName("org.postgresql.Driver");
	} catch (ClassNotFoundException e) {
	e.printStackTrace();
	}
	url="jdbc:postgresql://localhost:5433/mydb";
	user="studx";
	pass="studx";
	}

	


public ArrayList<CharDTO> getList(String charDivide, String searchType, String search) {
    if (charDivide.equals("전체")) {
        charDivide = "";
    }
    ArrayList<CharDTO> charlist = null;
    String sql = "";

    try {
        conn = DriverManager.getConnection(url, user, pass);

        if (searchType.equals("충전기")) {
            sql = "SELECT * FROM CHARGER WHERE charDivide LIKE ? AND CONCAT(charger_name) LIKE ?;";
        } else if (searchType.equals("대여한 사람")) {
            sql = "SELECT * FROM CHARGER WHERE prodDivide LIKE ? AND CONCAT(rental_name) LIKE ?;";
        }

        ps = conn.prepareStatement(sql);
        ps.setString(1, "%" + charDivide + "%");
        ps.setString(2, "%" + search + "%");

        rs = ps.executeQuery();
        List<CharDTO> list = new ArrayList<CharDTO>();

        while (rs.next()) {
            CharDTO dto = new CharDTO();
            dto.setCharger_name(rs.getString("charger_name"));
            dto.setCharger_port(rs.getString("charger_port"));
            dto.setCharger_output(rs.getString("charger_output"));
            dto.setCharger_quick(rs.getString("charger_quick"));
            dto.setCharger_date(rs.getString("charger_date"));
            dto.setCharger_rental(rs.getString("charger_rental"));
            dto.setRental_name(rs.getString("rental_name"));

            list.add(dto);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return charlist;
}
	


public int insertCharger(String charger_name, String charger_port, String charger_output, String charger_quick, String charger_date) throws SQLException, ParseException {
    String sql = "Insert into charger (charger_name, charger_port, charger_output, charger_quick, charger_date) values (?,?,?,?,?);";

    try {
        conn = DriverManager.getConnection(url, user, pass);
        ps = conn.prepareStatement(sql);

        ps.setString(1, charger_name);
        ps.setString(2, charger_port);
        ps.setString(3, charger_output);
        ps.setString(4, charger_quick);
        ps.setString(5, charger_date);

        

        int rec_no = ps.executeUpdate();
        return rec_no;
    } finally {
        if (ps != null)
            ps.close();
        if (conn != null)
            conn.close();
    }
}

/*
public int insertRental(String charger_name, String charger_date, String rental_name) throws SQLException, ParseException {
    String sql = "UPDATE charger SET charger_date=?, charger_rental=?, rental_name=? WHERE charger_name=?";

    try {
        conn = DriverManager.getConnection(url, user, pass);
        ps = conn.prepareStatement(sql);
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
        int ChargerDate = Integer.parseInt(charger_date);
        
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DATE, ChargerDate);

        String charger_rental = formatter.format(calendar.getTime());

        ps.setString(1, charger_date);
        ps.setString(2, charger_rental);
        ps.setString(3, rental_name);
        ps.setString(4, charger_name);

        int rec_no = ps.executeUpdate();
        return rec_no;
    } finally {
        if (ps != null)
            ps.close();
        if (conn != null)
            conn.close();
    }
}

*/





public List<CharDTO> findAllProducts() throws SQLException {
    String sql = "SELECT * FROM charger;";
    List<CharDTO> list = new ArrayList<CharDTO>();

    try {
        Connection conn = DriverManager.getConnection(url, user, pass);
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            CharDTO charger = new CharDTO();
            charger.setCharger_name(rs.getString("charger_name"));
            charger.setCharger_port(rs.getString("charger_port"));
            charger.setCharger_output(rs.getString("charger_output"));
            charger.setCharger_quick(rs.getString("charger_quick"));
            charger.setCharger_date(rs.getString("charger_date"));
            charger.setCharger_rental(rs.getString("charger_rental"));
            charger.setRental_name(rs.getString("rental_name"));
            list.add(charger);
        }

        return list;
    } finally {
        if (rs != null) rs.close();
        if (ps != null) ps.close();
        if (conn != null) conn.close();
    }
}



	
}
