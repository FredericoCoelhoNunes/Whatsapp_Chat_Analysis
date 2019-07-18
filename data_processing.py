"""
General functions for data processing
"""


from collections import Counter
from datetime import date
import re

import pandas as pd
import matplotlib.pyplot as plt


# Regular expression to parse a Whatsapp message.

WHATSAPP_MSG_PAT = r"(?P<date>.+?), (?P<time>.+?) - (?P<name>.+?):(?P<msg>.+)"
COMPILED_PAT = re.compile(WHATSAPP_MSG_PAT)


def generate_data_range(start_date, end_date):
	""" 
	Generates a pandas daterange.
	Input format: 'YYYY/MM/DD
	"""
	return pd.date_range(start=start_date, end=end_date)


def parse_msg(msg):
	"""
	Parses a WhatsApp message, separating it into date, time, name and message.
	"""
    parsed_msg = COMPILED_PAT.search(msg)
    return parsed_msg.groupdict()
    

def load_chat(chat_file):
	"""
	Loads the chat messages from the .txt file.
	"""
	with open(chat_file, 'r', encoding='utf-8') as f:
    	messages = f.readlines()

    return messages