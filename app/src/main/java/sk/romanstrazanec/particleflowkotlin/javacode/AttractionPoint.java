package sk.romanstrazanec.particleflowkotlin.javacode;

class AttractionPoint {
    float x, y;

    AttractionPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    void moveTo(float x, float y) {
        this.x = x;
        this.y = y;
    }

    void attract(Particle p) {
        p.x += Float.compare(x, p.x);
        p.y += Float.compare(y, p.y);
    }
}
