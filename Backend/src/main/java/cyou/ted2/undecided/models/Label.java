package cyou.ted2.undecided.models;

public enum Label {

    // languages + mat_icon name

    VEGAN("Vegan","Vegan","plant");

    String en, de, icon;

    Label(String en, String de, String icon) {
        this.en = en;
        this.de = de;
        this.icon = icon;
    }
}
