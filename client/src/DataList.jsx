/* 
Name: Keyur
ID: C0864901 */
import { useState, useEffect } from "react";
import axios from "axios";

function App() {
  const [items, setItems] = useState([]);
  const [inputValue, setInputValue] = useState("");
  const [inputContent, setContentValue] = useState("");
  const [editingId, setEditingId] = useState(null);

  useEffect(() => {
    fetchItems();
  }, []);
  const fetchItems = async () => {
    try {
      const response = await axios.get("http://localhost:3002/notes"); // Fetch items from the server
      setItems(response.data); // Update items state with server data
    } catch (error) {
      console.error("Error fetching items:", error);
    }
  };

  const handleInputChange = (e) => {
    setInputValue(e.target.value);
  };
  const handleContentChange = (e) => {
    setContentValue(e.target.value);
  };

  const handleCreate = async () => {
    try {
      const newItem = {
        title: inputValue,
        content: inputContent,
      };
      const response = await axios.post("http://localhost:3002/notes", newItem); // Create item on the server
      setItems([...items, response.data]); // Update items state with the new item from the server
      setInputValue("");
      setContentValue("");
    } catch (error) {
      console.error("Error creating item:", error);
    }
  };

  const handleEdit = (id) => {
    setEditingId(id);
    const selectedItem = items.find((item) => item._id === id);
    setInputValue(selectedItem.title);
    setContentValue(selectedItem.content);
  };

  const handleUpdate = async () => {
    try {
      const updatedItem = {
        title: inputValue,
        content: inputContent,
      };
      await axios.put(`http://localhost:3002/notes/${editingId}`, updatedItem); // Update item on the server
      const updatedItems = items.map((item) =>
        item._id === editingId
          ? { ...item, title: inputValue, content: inputContent }
          : item
      );
      setItems(updatedItems);
      setInputValue("");
      setContentValue("");
      setEditingId(null);
    } catch (error) {
      console.error("Error updating item:", error);
    }
  };

  const handleDelete = async (id) => {
    try {
      await axios.delete(`http://localhost:3002/notes/${id}`); // Delete item on the server
      const updatedItems = items.filter((item) => item._id !== id);
      setItems(updatedItems);
    } catch (error) {
      console.error("Error deleting item:", error);
    }
  };

  return (
    <div className="container mt-5">
      <h1 className="mb-4">CRUD App</h1>
      <div className="row mb-4">
        <div className="col-md-4 mb-2">
          <input
            type="text"
            value={inputValue}
            onChange={handleInputChange}
            placeholder="Enter item title"
            className="form-control"
          />
        </div>
        <div className="col-md-4 mb-2">
          <input
            type="text"
            value={inputContent}
            onChange={handleContentChange}
            placeholder="Enter item content"
            className="form-control"
          />
        </div>
        <div className="col-md-4 mb-2">
          {editingId === null ? (
            <button
              onClick={handleCreate}
              className="btn btn-primary btn-block"
            >
              Create
            </button>
          ) : (
            <button
              onClick={handleUpdate}
              className="btn btn-success btn-block"
            >
              Update
            </button>
          )}
        </div>
      </div>
      <ul className="list-group">
        {items.map((item) => (
          <li
            className="list-group-item d-flex justify-content-between align-items-center mb-2"
            key={item._id}
          >
            <span>
              <b>Title:</b> {item.title} <b>Content:</b> {item.content}
            </span>
            <div>
              <button
                onClick={() => handleEdit(item._id)}
                className="btn btn-warning mr-2"
              >
                Edit
              </button>{" "}
              <button
                onClick={() => handleDelete(item._id)}
                className="btn btn-danger"
              >
                Delete
              </button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
