# -*- coding: utf-8 -*-
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import Select
from selenium.common.exceptions import NoSuchElementException
from selenium.common.exceptions import NoAlertPresentException
import unittest, time, re, csv

class UntitledTestCase2(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Firefox()
        self.driver.implicitly_wait(10)
        self.base_url = "https://www.katalon.com/"
        self.verificationErrors = []
        self.accept_next_alert = True
    
    def test_untitled_test_case2(self):
        driver = self.driver
        with open('links.csv') as csvfile:
            reader = csv.reader(csvfile)
            with open('reviews.csv', 'w') as revfile:
                file = csv.writer(revfile, dialect='excel')
                for row in reader:
                    fname = row[0]
                    link = row[2]
                    title = ""
                    revtxt = ""
                    try:
                    	driver.get(link)
                    	time.sleep(1)
                    	try:
                    		driver.find_element_by_css_selector("span.moreBtn").click()
                    	except Exception as e:
                    		pass                    	

                    	title = driver.find_element_by_css_selector("span.noQuotes").text
                    	revtxt = driver.find_element_by_css_selector("p.partial_entry").text
                    except Exception as e:
                    	pass
                    
                    print(title)
                    print(revtxt)
                    print()
                    file.writerow([fname, title, revtxt])
                    

            # driver.find_element_by_css_selector("div.albumInfo").click()
            # time.sleep(2)
            # driver.find_element_by_link_text("Viewed").click()
            # time.sleep(2)
            # for num in range(1,662):
            #     image = driver.find_element_by_css_selector("#PANO_HOLDER > img")
            #     imgLink = image.get_attribute('src')
            #     print(imgLink)

            #     review = driver.find_element_by_css_selector("span.noQuotes > a")   
            #     reviewLink = review.get_attribute('href')         
            #     print(reviewLink)
            #     print()

            #     fname = "CASA"+str(num)
            #     file.writerow([fname, imgLink, reviewLink])
            #     # file.write(image.get_attribute('src'))
            #     # file.write('\n')
            #     driver.find_element_by_css_selector("div.heroNav.right.showOnHover").click()
            #     time.sleep(1)

            # driver.close()
            # file.close()
    
    def is_element_present(self, how, what):
        try: self.driver.find_element(by=how, value=what)
        except NoSuchElementException as e: return False
        return True
    
    def is_alert_present(self):
        try: self.driver.switch_to_alert()
        except NoAlertPresentException as e: return False
        return True
    
    def close_alert_and_get_its_text(self):
        try:
            alert = self.driver.switch_to_alert()
            alert_text = alert.text
            if self.accept_next_alert:
                alert.accept()
            else:
                alert.dismiss()
            return alert_text
        finally: self.accept_next_alert = True
    
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)

if __name__ == "__main__":
    unittest.main()
