package main

import (
	"fmt"
	"io/ioutil"
	"strings"
	"strconv"
	"regexp"
)

func main() {
	data, err := ioutil.ReadFile("4.input")
	if err != nil {
		fmt.Println("File reading error", err)
		return
	}

	lines := strings.Split(string(data), "\n")
	validPassports := 0
	passport := []string{}
	for i := 0; i < len(lines); i++ {
		line := lines[i]
		if line == "" || i == len(lines) - 1 {
			valid := validate2(passport)
			if valid {
				validPassports++
			}
			passport = []string{}
		} else {
			passport = append(passport, strings.Split(line, " ")...)
		}
	}

	fmt.Print(validPassports)
}

func validate2(passport []string) bool {
	recognizedFields := 0
	for i := 0; i < len(passport); i++ {
		split := strings.Split(passport[i], ":")
		field := split[0]
		value := split[1]

		if field == "cid" {
			continue
		}

		switch field {
		case "byr":
			recognizedFields++
			val, error := strconv.Atoi(value)
			if error != nil {
				return false
			}
			if val < 1920 || val > 2002 {
				return false
			}
		case "iyr":
			recognizedFields++
			val, error := strconv.Atoi(value)
			if error != nil {
				return false
			}
			if val < 2010 || val > 2020 {
				return false
			}
		case "eyr":
			recognizedFields++
			val, error := strconv.Atoi(value)
			if error != nil {
				return false
			}
			if val < 2020 || val > 2030 {
				return false
			}
		case "hgt":
			recognizedFields++
			re := regexp.MustCompile(`(\d+)(cm|in)`)
			extracted := re.FindAllStringSubmatch(value, -1)
			if len(extracted) == 0 {
				return false
			}
			parts := extracted[0]
			val, error := strconv.Atoi(parts[1])
			if error != nil {
				return false
			}
			if parts[2] == "cm" && (val < 150 || val > 193) {
				return false
			}
			if parts[2] == "in" && (val < 59 || val > 76) {
				return false
			}
		case "hcl":
			recognizedFields++
			re := regexp.MustCompile(`#[0-9a-f]{6}`)
			if !re.MatchString(value) {
				return false
			}
		case "ecl":
			recognizedFields++
			re := regexp.MustCompile(`(amb|blu|brn|gry|grn|hzl|oth)`)
			if !re.MatchString(value) {
				return false
			}
		case "pid":
			recognizedFields++
			re := regexp.MustCompile(`^\d{9}$`)
			if !re.MatchString(value) {
				return false
			}
		}
	}
	if recognizedFields < 7 {
		return false
	}
	return true
}

func validate(passport []string) bool {
	var names = map[string]bool {
		"byr": false,
			"iyr": false,
			"eyr": false,
			"hgt": false,
			"hcl": false,
			"ecl": false,
			"pid": false,
		}

	for i := 0; i < len(passport); i++ {
		field := strings.Split(passport[i], ":")[0]
		if field == "cid" {
			continue
		}
		names[field] = true
	}

	for _, v := range names {
		if !v {
			return false
		}
	}

	return true
}
