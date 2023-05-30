import java.util.LinkedList;


public class Category {

    private String name;
    private LinkedList questions = new LinkedList();

    public Category(String name) {
        this.name = name;
        //this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(LinkedList<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(int i) {

        questions.addLast(getName() + " Question " + i);

    }

    public void removeQuestion(){

        System.out.println(questions.removeFirst());

    }
}