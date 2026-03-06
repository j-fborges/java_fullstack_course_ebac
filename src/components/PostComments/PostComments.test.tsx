import { act, fireEvent, render, screen } from "@testing-library/react";
import Post from ".";
import PostComment from ".";
import userEvent from "@testing-library/user-event";

describe("Teste para o componente PostComment", () => {
  it("Deve renderizar o componente corretamente", () => {
    render(<PostComment />);
    expect(screen.getByText("Comentar")).toBeInTheDocument();
  });

  it("Adds two post comments correctly", () => {
    render(<PostComment />);
    const commentButton = screen.getByTestId("commentButton");
    const commentTextarea = screen.getByTestId("commentTextarea");

    
    fireEvent.change(commentTextarea, { target: { value: "a random comment 1" } });
    fireEvent.click(commentButton);
    const commentText = screen.getByText("a random comment 1")
    expect(commentText).toBeInTheDocument();
    
    fireEvent.change(commentTextarea, { target: { value: "another random comment 2" } });
    fireEvent.click(commentButton);
    const anotherCommentText = screen.getByText("another random comment 2")
    expect(anotherCommentText).toBeInTheDocument();
  });
});
