package br.com.borsoitech.pdv.model.type;

import java.util.ArrayList;
import java.util.List;

public class Pagina<T> {
    
    private List<T> content = new ArrayList<>();
    private boolean last;
    private Integer totalElements;
    private Integer totalPages;
    private Integer size;
    private Integer number;
    private Integer numberOfElements;
    private boolean first;
    private boolean empty;
    
    @Override
	public String toString() {
		return "Pagina [last=" + last + ", totalElements=" + totalElements + ", totalPages="
				+ totalPages + ", size=" + size + ", number=" + number + ", numberOfElements=" + numberOfElements
				+ ", first=" + first + ", empty=" + empty + "]";
	}

	public Pagina() { }

    public Pagina(boolean last, Integer totalElements, Integer totalPages, Integer size, Integer number, Integer numberOfElements, boolean first, boolean empty) {
        this.last = last;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.size = size;
        this.number = number;
        this.numberOfElements = numberOfElements;
        this.first = first;
        this.empty = empty;
    }

    public List<T> getContent() {
        return content;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}
