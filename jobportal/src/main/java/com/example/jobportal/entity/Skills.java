    package com.example.jobportal.entity;


    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @Table(name = "skills")
    public class Skills {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        private String name;

        private String experienceLevel;

        private String yearsOfExperience;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "job_seeker_profile")
        private JobSeekerProfile jobSeekerProfile;

    }
