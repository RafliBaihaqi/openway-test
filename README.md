# Selenium Java Test Case for Google User Access

Due to Google blocking Selenium access by default, we need to use a workaround by configuring the browser to use a specific user profile, allowing Selenium to bypass these restrictions and access Google services.

## Prerequisites

- **Java 8+**: Ensure that Java is installed and properly configured on your system.
- **Maven**: This project uses Maven to handle dependencies.
- **Selenium WebDriver**: Ensure you have Selenium WebDriver in your Maven dependencies.
- **Google Chrome**: The test case will use your Google Chrome profile to access the user account.

## Setup Instructions

### 1. Set Up Your Google Chrome Profile

Before running the test, you need to configure and find your Google Chrome user profile. Follow these steps:

#### a. Open Google Chrome
Open your Google Chrome browser and log in to the Google account you want to use for the test.

#### b. Create or Identify Your Chrome Profile

Google Chrome allows you to create and switch between multiple profiles. Here's how you can find or create a new profile:

1. Click on your profile icon in the upper-right corner of Chrome.
2. Select **Add** to create a new profile, or click on **Manage People** to view existing profiles.
3. Choose the profile you want to use for the test or create a new one and log in to the desired Google account.

#### c. Locate Your Google Chrome Profile Directory

Once you've selected the desired profile, follow these steps to locate its directory on your system:

- On **Windows**:
    - Go to the following directory:
      ```
      C:\Users\<YourUserName>\AppData\Local\Google\Chrome\User Data
      ```
    - Replace `<YourUserName>` with your actual Windows user name.
    - Inside the `User Data` folder, you'll find folders named `Profile 1`, `Profile 2`, etc. These correspond to different Google Chrome profiles.
    - For example, if you're using `Profile 20`, the path would be:
      ```
      C:\Users\<YourUserName>\AppData\Local\Google\Chrome\User Data\Profile 20
      ```

- On **macOS**:
    - The directory is located at:
      ```
      ~/Library/Application Support/Google/Chrome/User Data
      ```
    - Inside this folder, you'll find similar profile directories (`Profile 1`, `Profile 2`, etc.).

- On **Linux**:
    - The directory is typically located at:
      ```
      ~/.config/google-chrome/User Data
      ```

Once you have identified or created the profile you want to use, you can now proceed with configuring the Selenium WebDriver.

### 2. Configure User and Profile in the Code

In your `EmailDeleteTestCase.java`, you need to specify the user profile you just located. Update the following variables with the correct information:

```java
String user = "HP";  // Replace with your laptop user name
String profile = "Profile 20";  // Replace with the Chrome profile you want to use

ChromeOptions options = new ChromeOptions();
options.addArguments("user-data-dir=C:\\Users\\" + user + "\\AppData\\Local\\Google\\Chrome\\User Data");
options.addArguments("profile-directory=" + profile);
