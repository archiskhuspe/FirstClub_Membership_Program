-- USERS
INSERT INTO app_user (email, name) VALUES ('alice@example.com', 'Alice');
INSERT INTO app_user (email, name) VALUES ('bob@example.com', 'Bob');

-- TIERS
INSERT INTO tier (id, name, level, criteria_json, is_active, description) VALUES (1, 'Silver', 1, '{}', true, 'Entry tier');
INSERT INTO tier (id, name, level, criteria_json, is_active, description) VALUES (2, 'Gold', 2, '{}', true, 'Mid tier');
INSERT INTO tier (id, name, level, criteria_json, is_active, description) VALUES (3, 'Platinum', 3, '{}', true, 'Top tier');

-- BENEFITS
INSERT INTO benefit (id, name, description, type, benefit_value, applicable_to) VALUES (1, 'Free Delivery', 'Free delivery on eligible orders', 'delivery', 'free', 'all');
INSERT INTO benefit (id, name, description, type, benefit_value, applicable_to) VALUES (2, 'Extra 10% Discount', '10% off on selected items', 'discount', '10%', 'selected');
INSERT INTO benefit (id, name, description, type, benefit_value, applicable_to) VALUES (3, 'Priority Support', 'Priority customer support', 'support', 'priority', 'all');

-- TIER-BENEFIT MAPPINGS
INSERT INTO tier_benefit (id, tier_id, benefit_id, is_active) VALUES (1, 1, 1, true); -- Silver: Free Delivery
INSERT INTO tier_benefit (id, tier_id, benefit_id, is_active) VALUES (2, 2, 1, true); -- Gold: Free Delivery
INSERT INTO tier_benefit (id, tier_id, benefit_id, is_active) VALUES (3, 2, 2, true); -- Gold: Extra Discount
INSERT INTO tier_benefit (id, tier_id, benefit_id, is_active) VALUES (4, 3, 1, true); -- Platinum: Free Delivery
INSERT INTO tier_benefit (id, tier_id, benefit_id, is_active) VALUES (5, 3, 2, true); -- Platinum: Extra Discount
INSERT INTO tier_benefit (id, tier_id, benefit_id, is_active) VALUES (6, 3, 3, true); -- Platinum: Priority Support

-- PLANS (all permutations of plan type and tier)
-- Monthly
INSERT INTO plan (id, name, price, currency, duration_months, default_tier_id, is_active, description) VALUES (1, 'Monthly - Silver', 9.99, 'USD', 1, 1, true, 'Monthly plan, Silver tier');
INSERT INTO plan (id, name, price, currency, duration_months, default_tier_id, is_active, description) VALUES (2, 'Monthly - Gold', 14.99, 'USD', 1, 2, true, 'Monthly plan, Gold tier');
INSERT INTO plan (id, name, price, currency, duration_months, default_tier_id, is_active, description) VALUES (3, 'Monthly - Platinum', 19.99, 'USD', 1, 3, true, 'Monthly plan, Platinum tier');
-- Quarterly
INSERT INTO plan (id, name, price, currency, duration_months, default_tier_id, is_active, description) VALUES (4, 'Quarterly - Silver', 24.99, 'USD', 3, 1, true, 'Quarterly plan, Silver tier');
INSERT INTO plan (id, name, price, currency, duration_months, default_tier_id, is_active, description) VALUES (5, 'Quarterly - Gold', 34.99, 'USD', 3, 2, true, 'Quarterly plan, Gold tier');
INSERT INTO plan (id, name, price, currency, duration_months, default_tier_id, is_active, description) VALUES (6, 'Quarterly - Platinum', 49.99, 'USD', 3, 3, true, 'Quarterly plan, Platinum tier');
-- Yearly
INSERT INTO plan (id, name, price, currency, duration_months, default_tier_id, is_active, description) VALUES (7, 'Yearly - Silver', 89.99, 'USD', 12, 1, true, 'Yearly plan, Silver tier');
INSERT INTO plan (id, name, price, currency, duration_months, default_tier_id, is_active, description) VALUES (8, 'Yearly - Gold', 119.99, 'USD', 12, 2, true, 'Yearly plan, Gold tier');
INSERT INTO plan (id, name, price, currency, duration_months, default_tier_id, is_active, description) VALUES (9, 'Yearly - Platinum', 149.99, 'USD', 12, 3, true, 'Yearly plan, Platinum tier'); 