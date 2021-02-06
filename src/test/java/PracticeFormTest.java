import config.BaseTest;
import org.junit.jupiter.api.*;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTest extends BaseTest {

  String firstName = "Denis",
          lastName = "Andreyanov",
          email = "andreyanov.denis92@gmail.com",
          gender = "Male",
          phone = "1234567890",
          year = "1992",
          month = "December",
          day = "1",
          subject = "Computer Science",
          hobbies = "Sports",
          currentAddress = "Some currentAddress",
          state = "NCR",
          city = "Delhi",
          filename = "mem.jpg",
          filepath = "src/test/resources/";

  @Test
  void checkStudentRegistrationFormTest() {

    //��������� ����
    setText("#firstName", firstName);
    setText("#lastName", lastName);
    setText("#userEmail", email);
    $("#genterWrapper").$(byText(gender)).click();
    setText("#userNumber", phone);

    //������ � ����������
    $("#dateOfBirthInput").click();
    $(".react-datepicker__month-select").selectOption(month);
    $(".react-datepicker__year-select").selectOption(year);

    //��������� ����� - ���� ������ 10, �� ��������� � ������ 0
    if ( day.length() < 10 ) {
      day = "0" + day;
    }

    $(".react-datepicker__day--0" + day + "").click();

    //��������� ���� � �����
    $("#subjectsInput").setValue(subject).pressEnter();
    $("#hobbiesWrapper").$(byText(hobbies)).click();

    //�������� �����
    $("#uploadPicture").uploadFile(new File(filepath + filename));

    //���������� ������
    setText("#currentAddress", currentAddress);
    $("#react-select-3-input").setValue(state).pressEnter();
    $("#react-select-4-input").setValue(city).pressEnter();

    //���������� ������
    $("#submit").click();

    //��������� ���������� ������
    $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
    CheckTable("Student name", firstName + " " + lastName);
    CheckTable("Student Email", email);
    CheckTable("Gender", gender);
    CheckTable("Mobile", phone);
    CheckTable("Date of Birth", day + " " + month + "," + year);
    CheckTable("Subjects", subject);
    CheckTable("Hobbies", hobbies);
    CheckTable("Picture", filename);
    CheckTable("Address", currentAddress);
    CheckTable("State and City", state + " " + city);

  }

}
