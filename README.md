# Email Client Project

This Java project implements a simple command-line email client with the ability to send birthday greetings and regular emails to recipients. The email client supports two types of recipients: official and personal. Official recipients include friends, and each recipient is stored in a text file with specific format details. The application uses object serialization to save emails sent to the hard disk.

## Features

- **Recipient Types:**
  - Official: Format - `official: <name>, <email>, <designation>`
  - Office Friend: Format - `office_friend: <name>, <email>, <designation>, <birthday>`
  - Personal: Format - `personal: <name>, <nickname>, <email>, <birthday>`

- **Functionality:**
  - Add a new recipient through the command line.
  - Send birthday greetings to recipients with the correct message based on their type.
  - Send regular emails with specified subject and content.
  - Print names of recipients with birthdays on the current date.
  - Print details (subject and recipient) of all emails sent on a user-specified date.
  - Print the number of recipient objects in the application.

- **Serialization:**
  - Emails sent are saved as objects using serialization.

## Getting Started

1. **Clone the Repository:**
   ```bash
   git clone <repository_url>
   ```

2. **Compile and Run:**
   ```bash
   javac -cp lib/**/*.jar -d bin src/*.java
   java -cp bin:lib/**/*.jar EmailClient
   ```

3. **Usage:**
   - Follow the on-screen prompts to interact with the email client.
   - Options include adding recipients, sending emails, printing birthday lists, and more.

## Usage Examples

### Adding a New Recipient

```plaintext
Enter option type:
1 - Adding a new recipient
2 - Sending an email
3 - Printing out all the recipients who have birthdays on a given date
4 - Printing out details of all the emails sent on a particular day
5 - Printing out the number of recipient objects in the application
6 - Close the client

Enter option: 1

Insert details:
valid formats,
Official: <name>,<email>,<designation>
Office_friend: <name>,<email>,<designation>,<birthday>
Personal: <name>,<nickname>,<email>,<birthday>

Example: Official: John Doe,john.doe@example.com,Manager
Recipient added successfully
```

### Sending an Email

```plaintext
Enter option:
1 - Adding a new recipient
2 - Sending an email
3 - Printing out all the recipients who have birthdays on a given date
4 - Printing out details of all the emails sent on a particular day
5 - Printing out the number of recipient objects in the application
6 - Close the client

Enter option: 2

Enter the receiver's address: john.doe@example.com
Enter the subject of the email: Birthday Greetings
Enter your message: Wishing you a fantastic birthday, John!

Email sent
```

### Print Birthday Lists

```plaintext
Enter option:
1 - Adding a new recipient
2 - Sending an email
3 - Printing out all the recipients who have birthdays on a given date
4 - Printing out details of all the emails sent on a particular day
5 - Printing out the number of recipient objects in the application
6 - Close the client

Enter option: 3

Insert date (format - MM/dd): 10/09
John Doe
```

### Print Email Details by Date

```plaintext
Enter option:
1 - Adding a new recipient
2 - Sending an email
3 - Printing out all the recipients who have birthdays on a given date
4 - Printing out details of all the emails sent on a particular day
5 - Printing out the number of recipient objects in the application
6 - Close the client

Enter option: 4

Insert date (format - yyyy/MM/dd): 2023/10/09
Email Details:
- Subject: Birthday Greetings
- Recipient: John Doe
```

### Print Recipient Count

```plaintext
Enter option:
1 - Adding a new recipient
2 - Sending an email
3 - Printing out all the recipients who have birthdays on a given date
4 - Printing out details of all the emails sent on a particular day
5 - Printing out the number of recipient objects in the application
6 - Close the client

Enter option: 5

Number of recipients: 1
```

## Acknowledgments
- The `javax.mail` package is used for email functionality.
