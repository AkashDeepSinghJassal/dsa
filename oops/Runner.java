
interface Shape {
    public void draw();
    public void accept(Visitor v);
}

class Dot implements  Shape {

    @Override
    public void draw() {
        System.out.println("Draw dot");
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
class Square implements  Shape {

    @Override
    public void draw() {
        System.out.println("Draw square");
    }
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
class Visitor {
    void visit(Shape shape) {
        System.out.println("Visiting shape");
    }
    void visit(Dot dot) {
        System.out.println("visiting dot");
    }
    void visit(Square squre) {
        System.out.println("visiting squre");
    }
}

class Exporter {
    public void export(Shape shape) {
        System.out.println("Exporting shape");
    }
    public void export(Dot shape) {
        System.out.println("Exporting dot");
    }
    public void export(Square shape) {
        System.out.println("Exporting square");
    }
}

class Runner {
    public static void main(String[] args) {
        Runner runner = new Runner();

        Shape square = new Square();
        runner.drawShape(square);
        runner.exportShape(square);
        runner.visitShape(square);
    }

    void drawShape(Shape shape) {
        shape.draw();
    }
    void exportShape(Shape shape) {
        Exporter ex = new Exporter();
        ex.export(shape);
    }
    void visitShape(Shape shape){
        Visitor v = new Visitor();
        shape.accept(v);
    }
}
