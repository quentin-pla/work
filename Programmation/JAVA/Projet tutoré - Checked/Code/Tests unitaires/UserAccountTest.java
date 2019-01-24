package fr.univ_amu.iut;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public final class UserAccountTest {

    // **************************************************
    // Public methods tests
    // **************************************************

    @Test
    public final void testAddLevel() {
        UserAccount testUser = new UserAccount("testUser", "123", "FirstName",  "LastName", "user@test.com");
        testUser.setLevel(0);
        testUser.addLevel();
        assertTrue("testUser.level should be equals to 1",testUser.getLevel() == 1);
        testUser.addLevel();
        assertTrue("testUser.level should be equals to 2",testUser.getLevel() == 2);
    }

    @Test
    public final void testAddFavoriteUser() {
        UserAccount testUser = new UserAccount("testUser", "123", "FirstName",  "LastName", "user@test.com");
        UserAccount testUser2 = new UserAccount("testUser2", "123", "FirstName",  "LastName", "user2@test.com");
        testUser.addFavoriteUser(testUser2);
        assertTrue("Last UserAccount added to testUser.myFavoriteUsers should be testUser2",
                testUser.getMyFavoriteAccounts().get(testUser.getMyFavoriteAccounts().size()-1) == testUser2);
        assertTrue("testUser2.numberOfFollowers should be equals to 1",
                testUser2.getNumberOfFollowers() == 1);
    }

    @Test
    public final void testRemoveFavoriteUser() {
        UserAccount testUser = new UserAccount("testUser", "123", "FirstName",  "LastName", "user@test.com");
        UserAccount testUser2 = new UserAccount("testUser2", "123", "FirstName",  "LastName", "user2@test.com");
        testUser.addFavoriteUser(testUser2);
        testUser.removeFavoriteUser(testUser2);
        assertTrue("testUser.myFavoriteUsers should be empty",
                    testUser.getMyFavoriteAccounts().isEmpty());
        assertTrue("testUser2.numberOfFollowers should be equals to 0",
                   testUser2.getNumberOfFollowers() == 0);

    }

    @Test
    public final void testAddFavoriteProduct() {
//        UserAccount testUser = new UserAccount("testUser", "123", "FirstName",  "LastName", "user@test.com");
//        Category testCategory = new Category("testCategory","",1,1);
//        List<String> testTechnicalSheet = new ArrayList<>();
//        Product testProduct = new Product("testProduct","testBrand","fr/univ_amu/iut/Images/userBackground.jpg","A test product", testTechnicalSheet,testCategory);
//        testUser.addFavoriteProduct(testProduct);
//        assertTrue("Last Product added to testUser.myFavoriteProducts should be testProduct",
//                   testUser.getMyFavoriteProducts().get(testUser.getMyFavoriteProducts().size()-1) == testProduct);
    }

    @Test
    public final void testRemoveFavoriteProduct() {
        UserAccount testUser = new UserAccount("testUser", "123", "FirstName",  "LastName", "user@test.com");
        //Category testCategory = new Category("","testCategory");
        List<String> testTechnicalSheet = new ArrayList<>();
//        Product testProduct = new Product("testProduct","testBrand","","A test product", testTechnicalSheet,testCategory);
//        testUser.addFavoriteProduct(testProduct);
//        testUser.removeFavoriteProduct(testProduct);
//        assertTrue("testUser.myFavoriteProducts should be empty",
//                testUser.getMyFavoriteProducts().isEmpty());
    }

    @Test
    public final void testAddFavoriteComment () {
        UserAccount testUser = new UserAccount("testUser", "123", "FirstName",  "LastName", "user@test.com");
        //Category testCategory = new Category("","testCategory");
        List<String> testTechnicalSheet = new ArrayList<>();
//        Product testProduct = new Product("testProduct","testBrand","","A test product", testTechnicalSheet,testCategory);
        List<String> testPictures = new ArrayList<>();
        //Comment textComment = new Comment ("testComment", "I am a test comment", 4.5, testPictures, testUser, testProduct)
        //testUser.addFavoriteComment(testComment);
       // assertTrue("Last Comment added to testUser.myFavoriteComments should be testComment",
      //             testUser.getMyFavoriteComments().get(testUser.getMyFavoriteComments().size()-1) == testComment);
    }

    @Test
    public final void testRemoveFavoriteComment () {
        UserAccount testUser = new UserAccount("testUser", "123", "FirstName",  "LastName", "user@test.com");
        //Category testCategory = new Category("","testCategory");
        List<String> testTechnicalSheet = new ArrayList<>();
//        Product testProduct = new Product("testProduct","testBrand","","A test product", testTechnicalSheet,testCategory);
        List<String> testPictures = new ArrayList<>();
        //Comment textComment = new Comment ("testComment", "I am a test comment", 4.5, testPictures, testUser, testProduct)
        //testUser.addFavoriteComment(testComment);
        //testUser.removeFavoriteComment(testComment);
        // assertTrue("testUser.myFavoriteComments should be empty",
        //            testUser.getMyFavoriteComments().isEmpty());
    }

    @Test
    public final void testDeleteHistory() {
        UserAccount testUser = new UserAccount("testUser", "123", "FirstName",  "LastName", "user@test.com");
        testUser.addInHistory("A research");
        testUser.addInHistory("A research");
        testUser.addInHistory("A research");
        testUser.addInHistory("A research");
        testUser.deleteHistory();
        assertTrue("testUser.history should be empty",
                    testUser.getHistory().isEmpty());
    }

    @Test
    public final void testBanUser() {
        UserAccount testUser = new UserAccount("testUser", "123", "FirstName",  "LastName", "user@test.com");
        testUser.banUser(3600);
        assertTrue("First assert : testUser should be banned",
                    testUser.isUserBanned());
        assertTrue("Second assert : testUser should not be  permanently banned",
                    !testUser.isUserPermanentlyBanned());
        testUser.banUser(0);
        assertTrue("Third assert : testUser should be banned",
                testUser.isUserBanned());
        assertTrue("Fourth assert : testUser should be permanently banned",
                    testUser.isUserPermanentlyBanned());
    }

    @Test
    public final void testUnbanUser() {
        UserAccount testUser = new UserAccount("testUser", "123", "FirstName",  "LastName", "user@test.com");
        testUser.unbanUser();
        assertTrue("First assert : testUser should not be banned",
                    !testUser.isUserBanned());
        testUser.banUser(3600);
        testUser.unbanUser();
        assertTrue("Second assert : testUser should not be banned",
                    !testUser.isUserBanned());
        testUser.banUser(0);
        testUser.unbanUser();
        assertTrue("Third assert : testUser should not be permanently banned",
                    !testUser.isUserPermanentlyBanned());
    }

    @Test
    public final void testIsUserBanned() {
        UserAccount testUser = new UserAccount("testUser", "123", "FirstName",  "LastName", "user@test.com");
        testUser.banUser(3600);
        assertTrue("First assert : should return true",
                   testUser.isUserBanned());
        testUser.unbanUser();
        assertTrue("Second assert : should return false",
                    !testUser.isUserBanned());
    }

    @Test
    public final void testIsUserPermanentlyBanned() {
        UserAccount testUser = new UserAccount("testUser", "123", "FirstName",  "LastName", "user@test.com");
        testUser.banUser(0);
        assertTrue("First assert : should return true",
                testUser.isUserPermanentlyBanned());
        testUser.unbanUser();
        assertTrue("Second assert : should return false",
                !testUser.isUserPermanentlyBanned());
    }

    @Test
    public final void testSendAndReceivedMessage() {
        UserAccount testUser = new UserAccount("testUser", "123", "FirstName",  "LastName", "user@test.com");
        UserAccount testUser2 = new UserAccount("testUser2", "123", "FirstName",  "LastName", "user2@test.com");
        testUser.sendMessage("A test message", testUser2);
        assertTrue("testUser.sentMessages size should be 1",
                   testUser.getSentMessages().size() == 1);
        assertTrue("testUser2.receivedMessages size should be 1",
                   testUser2.getReceivedMessages().size() == 1);
        assertTrue("testUser.receivedMessages size should be 0",
                testUser.getReceivedMessages().size() == 0);
        assertTrue("testUser2.sentMessages size should be 0",
                testUser2.getSentMessages().size() == 0);
    }

    @Test
    public final void testSendNotification() {
        UserAccount testUser = new UserAccount("testUser", "123", "FirstName",  "LastName", "user@test.com");
        testUser.sendNotification("A test notification");
        assertTrue("testUser.notifications size should be 1",
                testUser.getNotifications().size() == 1);
    }

    // **************************************************
    // Public methods tests
    // **************************************************

    @Test
    public final void testAddFollower () {
        UserAccount testUser = new UserAccount("testUser", "123", "FirstName",  "LastName", "user@test.com");
        testUser.addFollower();
        testUser.addFollower();
        assertTrue("testUser.numberOfFollowers should be 2",
                   testUser.getNumberOfFollowers() == 2);
    }

    @Test
    public final void testRemoveFollower() {
        UserAccount testUser = new UserAccount("testUser", "123", "FirstName",  "LastName", "user@test.com");
        testUser.addFollower();
        testUser.addFollower();
        testUser.removeFollower();
        assertTrue("testUser.numberOfFollowers should be 1",
                testUser.getNumberOfFollowers() == 1);
    }
}
