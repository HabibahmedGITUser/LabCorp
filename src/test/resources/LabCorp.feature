Feature: LabCorp Job Search
  Scenario Outline: Verify Job Listing Information
    Given I open LabCorp website
    When I click on the Careers link
    And I search for the job position "<JobPosition>"
    And I select and browse to the job position "<JobPosition>"
    Then I confirm the job title as "<JobTitle>"
    And I confirm the job location as "<JobLocation>"
    And I confirm the job id as "<JobId>"
    And I assert additional information "<JobDetails_1>" "<JobDetails_2>" "<JobDetails_3>"
    When I click Apply Now
    Then I confirm the job title on the application page
    And I confirm the job location on the application page
    And I confirm the job ID on the application page
    And I assert additional information on the application page
    When I click to Return to Job Search
    Then I am back on the Job Search page

    Examples:
               | JobPosition            | JobTitle               | JobLocation                     | JobId   | JobDetails_1                                                       | JobDetails_2                                                                                                     | JobDetails_3 |
               | Sr MDM Developer       | Sr MDM Developer       |  Bangalore, India               | 2333468 | Develop customizations for the currently implemented MDM Product . | At least 4 years of experience with IBM InfoSphere MDM Advanced Edition  development and implementation skills . | At least 4 years of working experience in the SOA technologies, tools, and governance .|
