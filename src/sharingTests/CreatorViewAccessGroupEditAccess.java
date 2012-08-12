package sharingTests;

import static org.junit.Assert.*;

import org.junit.Test;

import share.ShareSeleniumTest;

public class CreatorViewAccessGroupEditAccess {

	ShareSeleniumTest sel = new ShareSeleniumTest();
	String Title;
	String[] CreatedValue = new String[4];
	String[] ReadValue = new String[4];
	
	@Test
	public void test() throws Exception {
		
		String UserName = "testuser2";
		String PassWord = "nterop_4dmin";
		
		//Navigate to the Login Page
		sel.setUp();
		
		//Login
		Title = sel.Login(UserName, PassWord);
		assertEquals(Title, "eParade - My Items");
		
		//Create Item and Verify that it is appeared in the table
		Title = sel.CreateItem();
		assertEquals(Title, "eParade - My Items");
		sel.SortandReadFirstRowMyItems();
		CreatedValue = sel.getCreateValue();
		ReadValue = sel.getReadValue();
		for (int i = 0; i < 3; i++ )
		{
			assertEquals(CreatedValue[i], ReadValue[i]);
		}
		
		//Assign the 'Edit' permission to both users
		sel.ShareSettingsUser(false, true);
				
		sel.closeEditArea();
		assertTrue(sel.getEditAccess());
		
		
		//Validate that the user has the right permission
		sel.ViewItem();
		assertFalse(sel.EditItemAvailable());
	
		//Login as testUser1
		UserName = "testuser1";
		sel.LogOutandRelogin(UserName, PassWord);
				
		
		//Validate that the user has the right permission
		sel.ViewItem();
		assertTrue(sel.EditItemAvailable());
	
	} 

}
