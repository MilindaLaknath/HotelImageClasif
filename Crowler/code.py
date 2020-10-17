# -*- coding: utf-8 -*-
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import Select
from selenium.common.exceptions import NoSuchElementException
from selenium.common.exceptions import NoAlertPresentException
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities
import unittest, time, re, csv

class UntitledTestCase2(unittest.TestCase):
    def setUp(self):
        # cap = DesiredCapabilities().FIREFOX
        # cap["marionette"] = False
        # self.driver = webdriver.Firefox(capabilities=cap)
        # self.driver = webdriver.Firefox(capabilities=cap, executable_path="/usr/local/bin/geckodriver")
        self.driver = webdriver.Chrome("/usr/bin/chromedriver");
        self.driver.implicitly_wait(30)
        self.base_url = "https://www.katalon.com/"
        self.verificationErrors = []
        self.accept_next_alert = True
    
    def test_untitled_test_case2(self):
        driver = self.driver
        with open('links.csv', 'w') as csvfile:
            file = csv.writer(csvfile, dialect='excel')
            # file = open("links.csv","a")
            driver.get("https://www.tripadvisor.com/Hotel_Review-g2424710-d316580-Reviews-Nilaveli_Beach_Hotel-Nilaveli_Eastern_Province.html#photos;aggregationId=101&albumid=101&filter=2&ff=24567481")
            
            # driver.find_element_by_css_selector("div.albumInfo").click()
            # time.sleep(2)
            # driver.find_element_by_link_text("Viewed").click()
            time.sleep(2)
            for num in range(1,393):
                imgLink = ""
                try:
                    image = driver.find_element_by_css_selector("#PANO_HOLDER > img")
                    imgLink = image.get_attribute('src')
                except Exception, e:
                    pass
                
                print(num)
                print(imgLink)

                reviewLink = ""
                try:
                    review = driver.find_element_by_css_selector("div.captionTitle > a")   
                    reviewLink = review.get_attribute('href') 
                except Exception as e:    
                	pass               
                        
                print(reviewLink)
                print()

                fname = "NilaveliBeachHotel"+str(num)
                file.writerow([fname, imgLink, reviewLink])
                # file.write(image.get_attribute('src'))
                # file.write('\n')
                driver.find_element_by_css_selector("div.heroNav.right.showOnHover").click()
                time.sleep(3)

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
