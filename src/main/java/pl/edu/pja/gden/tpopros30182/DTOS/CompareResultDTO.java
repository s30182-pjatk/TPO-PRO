package pl.edu.pja.gden.tpopros30182.DTOS;

public class CompareResultDTO {
    private FigureDTO figureOne;
    private FigureDTO figureTwo;
    private Double score;

    public CompareResultDTO() {
    }

    public FigureDTO getFigureOne() {
        return figureOne;
    }

    public void setFigureOne(FigureDTO figureOne) {
        this.figureOne = figureOne;
    }

    public FigureDTO getFigureTwo() {
        return figureTwo;
    }

    public void setFigureTwo(FigureDTO figureTwo) {
        this.figureTwo = figureTwo;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
