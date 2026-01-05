package pl.edu.pja.gden.tpopros30182.DTOS;

public class AddExistingDTO {
    private Long figureId;
    private Long userId;

    public AddExistingDTO() {
    }

    public Long getFigureId() {
        return figureId;
    }

    public void setFigureId(Long figureId) {
        this.figureId = figureId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
