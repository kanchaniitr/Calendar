import React, { useState } from "react";

function Dropdown() {
  const [selected, setSelected] = useState("Select an option");
  const [open, setOpen] = useState(false);
  const options = ["Today", "This Week", "This Month", "All Time"];

  return (
    <div className="relative inline-block text-left">
      <div>
        <button
          onClick={() => setOpen(!open)}
          className="bg-blue-600 text-white px-4 py-2 rounded-md shadow"
        >
          {selected}
        </button>
      </div>

      {open && (
        <div className="absolute z-10 mt-2 w-48 bg-white border rounded-md shadow">
          {options.map((option) => (
            <div
              key={option}
              onClick={() => {
                setSelected(option);
                setOpen(false);
              }}
              className="px-4 py-2 hover:bg-blue-100 cursor-pointer"
            >
              {option}
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default Dropdown;