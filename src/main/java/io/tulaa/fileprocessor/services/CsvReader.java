package io.tulaa.fileprocessor.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.tulaa.fileprocessor.models.User;
import io.tulaa.fileprocessor.repositories.UserRepository;

@Service
public class CsvReader {
	@Autowired
	UserRepository userRepository;

	public boolean processFile(File csvFile) {
		try {
			readFile(csvFile);
			return true;
		} catch (Exception e) {

		}
		return false;
	}

	private void readFile(File csvFile) {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {
			DateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
			int counter = 0;
			br = new BufferedReader(new FileReader(csvFile));
			int firstNameIdx = -1;
			int lastNameIdx = -1;
			int dateOfBirthIdx = -1;
			int postalAddressIdx = -1;
			int nationalIdx = -1;
			int genderIdx = -1;

			try {
				List<User> users = new ArrayList<User>();
				while ((line = br.readLine()) != null) {
					if (counter == 0) {
						//For the first line in the file, get the column headers and map the indexes
						String[] header = line.split(cvsSplitBy);

						for (int i = 0; i < header.length; i++) {
							if (header[i].trim().equalsIgnoreCase("first_name")) {
								firstNameIdx = i;
							} else if (header[i].trim().equalsIgnoreCase("last_name")) {
								lastNameIdx = i;
							} else if (header[i].trim().equalsIgnoreCase("date_of_birth")) {
								dateOfBirthIdx = i;
							} else if (header[i].trim().equalsIgnoreCase("postal_address")) {
								postalAddressIdx = i;
							} else if (header[i].trim().equalsIgnoreCase("national_id")) {
								nationalIdx = i;
							} else if (header[i].trim().equalsIgnoreCase("gender")) {
								genderIdx = i;
							}
						}

					} else {
						// use comma as separator
						String[] userData = line.split(cvsSplitBy);
						if (userData.length >= 6) {
							User user = new User();
							user.setDateOfBirth(sdf.parse(userData[dateOfBirthIdx]));
							user.setFirstName(userData[firstNameIdx]);
							user.setGender(userData[genderIdx]);
							user.setLastName(userData[lastNameIdx]);
							user.setNationalId(userData[nationalIdx]);
							user.setPostalAddress(userData[postalAddressIdx]);
							//check if user exists and if not add the user to the users list to be saved later
							User existingUser = userRepository.findByNationalId(user.getNationalId());
							if (existingUser == null) {
								users.add(user);
							}
						}

					}
					counter++;

				}
				if (!users.isEmpty()) {
					userRepository.saveAll(users);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
