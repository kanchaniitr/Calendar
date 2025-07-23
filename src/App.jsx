import React from "react";
import Dropdown from "./Dropdown";

import "./index.css";

const locales = {
  "en-US": require("date-fns/locale/en-US"),
};

const localizer = dateFnsLocalizer({
  format,
  parse,
  startOfWeek,
  getDay,
  locales,
});

const App = () => {
  const [events, setEvents] = useState([
    {
      title: "Vacation Calendar",
      start: new Date(),
      end: new Date(new Date().getTime() + 60 * 60 * 1000),
    },
  ]);

  return (
    <div className="h-screen p-4 bg-gray-100">
      <h1 className="text-3xl font-bold mb-4">📅 Calendar App</h1>
      <div className="p-10">
            <h1 className="text-2xl mb-4">Countries</h1>
            <Dropdown />
          </div>
      <Calendar
        localizer={localizer}
        events={events}
        startAccessor="start"
        endAccessor="end"
        style={{ height: 500 }}
      />
    </div>
  );
};

export default App;