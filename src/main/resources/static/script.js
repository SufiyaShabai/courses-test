async function fetchCourses(category) {
    const url = category ? `/api/courses/category/${category}` : "/api/courses/all";
    const res = await fetch(url);
    const data = await res.json();
    const list = document.getElementById("courseList");
    list.innerHTML = "";
    data.forEach(course => {
        const li = document.createElement("li");
        li.innerHTML = `<strong>${course.title}</strong> - ${course.description}`;
        list.appendChild(li);
    });
}

async function fetchEnrollment() {
    const res = await fetch("/api/courses/enrolled-count");
    const data = await res.json();
    const list = document.getElementById("courseList");
    list.innerHTML = "";
    for (let title in data) {
        const li = document.createElement("li");
        li.innerText = `${title}: ${data[title]} enrolled`;
        list.appendChild(li);
    }
}
