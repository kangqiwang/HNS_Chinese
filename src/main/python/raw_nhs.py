import json

import requests

def nhs_scraper(url):
    """
    2b4002aa80684907970da3ca540b6712
    This function scrapes the NHS website for the latest data on COVID-19 cases in the UK.
    :param url: The URL of the NHS website.
    :param file_name: The name of the file to save the data to.
    :return: None
    """
    # Get the data from the NHS website
    response = requests.get(url,headers={'subscription-key': '2b4002aa80684907970da3ca540b6712'})
    # Convert the data to JSON
    data = json.loads(response.text)
    # Save the data to a file
    # with open(file_name, 'w') as outfile:
    #     json.dump(data, outfile)
    
    for condition in data["significantLink"]:
        print(condition["name"])
    # print(len(data["significantLink"]))
    
# URL of the NHS website
nhs_scraper("https://api.nhs.uk/conditions")


