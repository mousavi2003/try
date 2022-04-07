package Controller;

import Model.People;

public class MainMenuController extends RegisterMenuController{
    private People people= new People();

    public People getPeople() {
        return this.people;
    }


}
