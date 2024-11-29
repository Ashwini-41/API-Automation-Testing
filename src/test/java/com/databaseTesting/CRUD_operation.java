package com.databaseTesting;

import static org.testng.Assert.assertEquals;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

public class CRUD_operation extends BaseClass {

	@Test
	public void InsertData_DBTest() throws SQLException{
		con = this.setUp();
		try {
		PreparedStatement ps = con.prepareStatement("insert into Departments values(?,?)");
		ps.setInt(1, 5);
		ps.setString(2, "Hiring");
		ps.executeUpdate();
		}catch(SQLException sqe) {
			System.out.println(sqe.getErrorCode());
			System.out.println(sqe.getSQLState());
			System.out.println(sqe.getMessage());
			sqe.printStackTrace();
		}
		
	}
	
	@Test
	public void UpdateData_DBTest() throws SQLException {
		con = this.setUp();
		PreparedStatement ps = con.prepareStatement("update Departments set department_name='Marketing' where department_id=4");
		ps.executeUpdate();
	}
	
	@Test
	public void DeleteData_DBTest() throws SQLException {
		
		con = this.setUp();
		
		try {
		PreparedStatement ps = con.prepareStatement("Delete from Projects where department_id = 3");
		ps.execute();
		}catch(SQLException sqe) {
			System.out.println(sqe.getErrorCode());
			System.out.println(sqe.getSQLState());
			System.out.println(sqe.getMessage());
			sqe.printStackTrace();
		}
		
	}
	
	@Test
	public void SelectQuery_DBTest() throws SQLException{
		con = this.setUp();
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery("Select * from Departments");
		while(rs.next()) {
			String Department_id=rs.getString(1);
			String Department_name=rs.getString(2);
			
			System.out.println(Department_id+ " " + Department_name);
		}
	}
	
	
	
	  @Test
    public void UpdateEmployeeSalary_DBTest() throws SQLException {
        String query = "UPDATE Employees SET salary = 80000 WHERE employee_id = 1";
        PreparedStatement stmt = con.prepareStatement(query);
        int rowsAffected = stmt.executeUpdate();
        assertEquals(1, rowsAffected, "Update failed!");

        // Verify the update
        String verifyQuery = "SELECT salary FROM Employees WHERE employee_id = 1";
        ResultSet rs = con.prepareStatement(verifyQuery).executeQuery();
        if (rs.next()) {
            assertEquals(80000, rs.getInt("salary"), "Salary update verification failed!");
        }
    }
/*
    @Test
    public void testDeleteProject() throws SQLException {
        String query = "DELETE FROM Projects WHERE project_name = 'Test Project'";
        PreparedStatement stmt = connection.prepareStatement(query);
        int rowsAffected = stmt.executeUpdate();
        assertEquals(1, rowsAffected, "Delete failed!");
    }

    @Test
    public void testFetchEmployeesJoinedAfter2020() throws SQLException {
        String query = "SELECT * FROM Employees WHERE joining_date > '2020-01-01'";
        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        assertTrue(rs.next(), "No employees found who joined after 2020!");
    }
	 */
//	@Test
//	public void
}
