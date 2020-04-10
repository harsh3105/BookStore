window.sr = ScrollReveal();
sr.reveal(".about-books", {
    origin:"left",
    duration: 2000
});

sr.reveal(".long-text", {
    origin: "right",
    duration: 2000
});


sr.reveal(".card", {
    origin: "bottom",
    duration: 1000,
    delay: 1000
});


sr.reveal("iframe",{
    origin: "top",
    duration: 1000,
    distance: "300px",
    delay: 800
});

sr.reveal("#location-desc", {
    origin: "right",
    duration: 1000,
    distance: "300px",
    delay: 800
});

