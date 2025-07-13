# iso-countries

iso-countries is a Clojure library designed to provide easy access to comprehensive ISO 3166-1 country data.

This library aims to simplify the process of retrieving country-specific information, including official names,
alpha-2 and alpha-3 codes, numeric codes, regional classifications, and unicode flags.

## installation

Add iso-countries to dependency list

```clojure
[org.clojars.jj/iso-countries "1.2.3"]
```

## Usage

```clojure
(iso/get-country :tonga)
```

### Country and territory list

| Name                                          |
|-----------------------------------------------|
| :afghanistan                                  |
| :albania                                      |
| :algeria                                      |
| :american-samoa                               |
| :andorra                                      |
| :angola                                       |
| :anguilla                                     |
| :antarctica                                   |
| :antigua-and-barbuda                          |
| :argentina                                    |
| :armenia                                      |
| :aruba                                        |
| :australia                                    |
| :austria                                      |
| :azerbaijan                                   |
| :bahamas                                      |
| :bahrain                                      |
| :bangladesh                                   |
| :barbados                                     |
| :belarus                                      |
| :belgium                                      |
| :belize                                       |
| :benin                                        |
| :bermuda                                      |
| :bhutan                                       |
| :bolivia                                      |
| :bosnia-and-herzegovina                       |
| :botswana                                     |
| :bouvet-island                                |
| :brazil                                       |
| :british-indian-ocean-territory               |
| :british-virgin-islands                       |
| :brunei-darussalam                            |
| :bulgaria                                     |
| :burkina-faso                                 |
| :burundi                                      |
| :cabo-verde                                   |
| :cambodia                                     |
| :cameroon                                     |
| :canada                                       |
| :caribbean-netherlands                        |
| :cayman-islands                               |
| :central-african-republic                     |
| :chad                                         |
| :chile                                        |
| :china                                        |
| :christmas-island                             |
| :cocos-keeling-islands                        |
| :colombia                                     |
| :comoros                                      |
| :congo                                        |
| :cook-islands                                 |
| :costa-rica                                   |
| :croatia                                      |
| :cuba                                         |
| :curacao                                      |
| :cyprus                                       |
| :czechia                                      |
| :denmark                                      |
| :djibouti                                     |
| :dominica                                     |
| :dominican-republic                           |
| :ecuador                                      |
| :egypt                                        |
| :el-salvador                                  |
| :equatorial-guinea                            |
| :eritrea                                      |
| :estonia                                      |
| :eswatini                                     |
| :ethiopia                                     |
| :falkland-islands                             |
| :faroe-islands                                |
| :federated-states-of-micronesia               |
| :fiji                                         |
| :finland                                      |
| :france                                       |
| :french-guiana                                |
| :french-polynesia                             |
| :french-southern-territories                  |
| :gabon                                        |
| :gambia                                       |
| :georgia                                      |
| :germany                                      |
| :ghana                                        |
| :gibraltar                                    |
| :greece                                       |
| :greenland                                    |
| :grenada                                      |
| :guadeloupe                                   |
| :guam                                         |
| :guatemala                                    |
| :guernsey                                     |
| :guinea                                       |
| :guinea-bissau                                |
| :guyana                                       |
| :haiti                                        |
| :heard-island-and-mcdonald-islands            |
| :holy-see                                     |
| :honduras                                     |
| :hong-kong                                    |
| :hungary                                      |
| :iceland                                      |
| :india                                        |
| :indonesia                                    |
| :iran                                         |
| :iraq                                         |
| :ireland                                      |
| :isle-of-man                                  |
| :israel                                       |
| :italy                                        |
| :ivory-coast                                  |
| :jamaica                                      |
| :japan                                        |
| :jersey                                       |
| :jordan                                       |
| :kazakhstan                                   |
| :kenya                                        |
| :kiribati                                     |
| :kuwait                                       |
| :kyrgyzstan                                   |
| :land-islands                                 |
| :laos                                         |
| :latvia                                       |
| :lebanon                                      |
| :lesotho                                      |
| :liberia                                      |
| :libya                                        |
| :liechtenstein                                |
| :lithuania                                    |
| :luxembourg                                   |
| :macao                                        |
| :madagascar                                   |
| :malawi                                       |
| :malaysia                                     |
| :maldives                                     |
| :mali                                         |
| :malta                                        |
| :marshall-islands                             |
| :martinique                                   |
| :mauritania                                   |
| :mauritius                                    |
| :mayotte                                      |
| :mexico                                       |
| :moldova                                      |
| :monaco                                       |
| :mongolia                                     |
| :montenegro                                   |
| :montserrat                                   |
| :morocco                                      |
| :mozambique                                   |
| :myanmar                                      |
| :namibia                                      |
| :nauru                                        |
| :nepal                                        |
| :netherlands                                  |
| :new-caledonia                                |
| :new-zealand                                  |
| :nicaragua                                    |
| :niger                                        |
| :nigeria                                      |
| :niue                                         |
| :norfolk-island                               |
| :north-korea                                  |
| :north-macedonia                              |
| :northern-mariana-islands                     |
| :norway                                       |
| :oman                                         |
| :pakistan                                     |
| :palau                                        |
| :palestine                                    |
| :panama                                       |
| :papua-new-guinea                             |
| :paraguay                                     |
| :peru                                         |
| :philippines                                  |
| :pitcairn                                     |
| :poland                                       |
| :portugal                                     |
| :puerto-rico                                  |
| :qatar                                        |
| :republic-of-the-congo                        |
| :reunion                                      |
| :romania                                      |
| :russia                                       |
| :rwanda                                       |
| :saint-barthelemy                             |
| :saint-helena-ascension-and-tristan-da-cunha  |
| :saint-kitts-and-nevis                        |
| :saint-lucia                                  |
| :saint-martin-french-part                     |
| :saint-pierre-and-miquelon                    |
| :saint-vincent-and-the-grenadines             |
| :samoa                                        |
| :san-marino                                   |
| :sao-tome-and-principe                        |
| :saudi-arabia                                 |
| :senegal                                      |
| :serbia                                       |
| :seychelles                                   |
| :sierra-leone                                 |
| :singapore                                    |
| :sint-maarten-dutch-part                      |
| :slovakia                                     |
| :slovenia                                     |
| :solomon-islands                              |
| :somalia                                      |
| :south-africa                                 |
| :south-georgia-and-the-south-sandwich-islands |
| :south-korea                                  |
| :south-sudan                                  |
| :spain                                        |
| :sri-lanka                                    |
| :sudan                                        |
| :suriname                                     |
| :svalbard-and-jan-mayen                       |
| :sweden                                       |
| :switzerland                                  |
| :syrian-arab-republic                         |
| :turkey                                       |
| :taiwan-province-of-china                     |
| :tajikistan                                   |
| :tanzania                                     |
| :thailand                                     |
| :timor-leste                                  |
| :togo                                         |
| :tokelau                                      |
| :tonga                                        |
| :trinidad-and-tobago                          |
| :tunisia                                      |
| :turkmenistan                                 |
| :turks-and-caicos-islands                     |
| :tuvalu                                       |
| :uganda                                       |
| :ukraine                                      |
| :united-arab-emirates                         |
| :united-kingdom                               |
| :united-states-minor-outlying-islands         |
| :united-states-of-america                     |
| :uruguay                                      |
| :uzbekistan                                   |
| :vanuatu                                      |
| :venezuela                                    |
| :vietnam                                      |
| :virgin-islands-us                            |
| :wallis-and-futuna                            |
| :western-sahara                               |
| :yemen                                        |
| :zambia                                       |
| :zimbabwe                                     |

## License

Copyright © 2025 [ruroru](https://github.com/ruroru)

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
https://www.eclipse.org/legal/epl-2.0/.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
